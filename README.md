### recommend
elastic search java客户端，使用retrofit + rxjava2 沟通elk的rest端点进行索引，文档和查询相关操作。 使用简单，支持elk多节点，支持响应式编程特性，适合脱离服务端单独在android项目中使用。

### quick start
1. clone项目
```shell
git clone git@github.com:Tinysakura/smart_elk_client.git
```
2. maven编译
```shell
mvn clean install -Dmaven.test.skip=true
```
3. pom中引入
```xml
<dependencies>
	<dependency>
            <groupId>com.tinysakura</groupId>
            <artifactId>smart_elk_client</artifactId>
            <version>1.0-RELEASE</version>
        </dependency>
</dependencies>
```

### usage
**1.配置elk节点**

如果你是spring-boot或其他spring项目，在resource path下的application.properties或application.yml文件中配置elk.node.ip属性（推荐将主节点或可用性较高的节点放在首位，节点地址由,分隔）：
```
elk.node.ip = http://192.168.1.1:9200,http://127.0.0.1:9200
```
如果是普通java项目，则需要在resource path下手动创建application.properties文件。

**2.创建索引**

```java
 	@Test
    public void createIndexTest() {
        IndexService indexService = RetrofitProxyServiceHolder.getInstance().getIndexServiceProxy();

        Analyzer.Builder analyzerBuilder = new Analyzer.Builder();
        Analyzer analyzer = analyzerBuilder.name("chinese").tokenizer("ik_max_word").build();

        Properties.Builder propBuilder = new Properties.Builder();
        Properties properties = propBuilder.name("author").type(DocumentPropertiesConstant.Type.TEXT).analyzer("ik_max_word").name("title").analyzer("ik_max_word").type(DocumentPropertiesConstant.Type.TEXT).build();
        
        DocumentType.Builder docBuilder = new DocumentType.Builder();
        DocumentType documentType = docBuilder.name("book").properties(properties).build();

        Index.Builder indexBuilder = new Index.Builder();
        Index index = indexBuilder.indexName("book").replicasNumber(1).shardsNumber(4).analysis(analyzer).mapping(documentType).build();

        indexService.createIndex(index.getIndexName(), index.getIndex()).subscribe(new Consumer<Acknowledged>() {
            @Override
            public void accept(Acknowledged acknowledged) throws Exception {
                System.out.println(acknowledged);
            }
        });
    }
```
首先从RetrofitProxyServiceHolder这个单例类中获取索引操作相关的IndexService代理对象，之后使用构造器类自定义分析器、映射配置、索引配置，最后使用indexService的createIndex方法创建索引。构造器的设计遵循elk的rest api，所有构造器类都位于项目的com.tinysakura.core包下，使用者可以参考源码和elk的rest api进行使用。

**3 为索引添加文档**
**3.1 单个文档的索引**

```java
    @Test
    public void addDocumentTest() {
        DocumentService documentService = RetrofitProxyServiceHolder.getInstance().getDocumentServiceProxy();

        Book book = new Book();
        book.setTitle("冰与火之歌");
        book.setAuthor("乔治·马丁");

        documentService.postDocument("book", "book", book).subscribe(new Consumer<Acknowledged>() {
            @Override
            public void accept(Acknowledged acknowledged) throws Exception {
                System.out.println(acknowledged);
            }
        });
    }
```
文档的照例从RetrofitProxyServiceHolder这个单例类中获取文档操作相关的DocumentService代理对象，调用postDocument方法即可。三个参数分别对应索引，文档类型与具体文档。

**3.2 批量文档的索引**

```java
	@Test
    public void batchPostDocumentTest() {
        BulkItem bulkItem1 = new BulkItem.Builder().operation(BulkOperationConstants.Operation.CREATE).document(new Music("Taylor swift", "Style"))
                .documentId("3").index("media").type(BulkOperationConstants.Type.CONCAT).build();

        BulkItem bulkItem2 = new BulkItem.Builder().operation(BulkOperationConstants.Operation.CREATE).document(new Music("Taylor swift", "Love Story"))
                .documentId("4").index("media").type(BulkOperationConstants.Type.CONCAT).build();

        RequestBody requestBody = new Bulk.Builder().item(bulkItem1).item(bulkItem2).build();

        DocumentService documentService = RetrofitProxyServiceHolder.getInstance().getDocumentServiceProxy();
        documentService.batchPostDocument(requestBody).subscribe(new Consumer<JsonObject>() {
            public void accept(JsonObject bulkResult) throws Exception {
                System.out.println(bulkResult);
            }
        });
    }
```
每个文档对应一个BulkItem，创建好所有的BulkItem后传入Bulk的构造器类，注意build方法的返回结果为一个RequestBody，之后调用DocumentService接口的batchPostDocument方法即可完成多文档的索引。
当然你也可以直接使用文件创建RequestBody，文件内容的格式需要遵循elk批量索引的语法：
```java
RequestBody requestBody = Bulk.fromJsonFile("fielpath");
```

**4 查询**

smart-elk-client支持几乎所有类型的查询，包括词条查询，match查询，模糊查询，标识符查询，lucene语法查询，范围查询，正则查询，通配符查询以及复合查询（布尔查询与加权查询）。支持对查询结果进行过滤与排序，高亮显示。
下面是一个match查询的示例:
```java
	@Test
    public void matchQueryTest() {
        QueryService queryService = RetrofitProxyServiceHolder.getInstance().getQueryServiceProxy();

        MatchQuery.Builder matchQueryBuilder = new MatchQuery.Builder();
        MatchQuery matchQuery = matchQueryBuilder.field("title").query("冰与火之歌").analyzer("ik_max_word").build();

        QueryBody.Builder queryBodyBuilder = new QueryBody.Builder();
        QueryBody queryBody = QueryBody queryBody = queryBodyBuilder.from(0).size(10).query(matchQuery.getQuery()).build();
        
        queryService.search("book", "book", queryBody.getQueryBody(), Book.class).subscribeOn(Schedulers.io()).subscribe(new Consumer<QueryResponse>() {
            @Override
            public void accept(QueryResponse queryResponse) throws Exception {
                System.out.println(queryResponse.getResults());
            }
        });
    }
```
所有查询都需要先构建Query主体，每种类型的查询的构建类具有不同的操作符，可以参照elk的rest api和构造器源码(所有查询构造器都位于com.tinysakura.core.query包下)使用。
Query主体构建完毕后构建通用的QueryBody，这里可以指定一些查询属性、定义过滤器、定义排序条件等。同样可以参照elk的rest api和项目源码使用。
QueryBody构建完毕后传入queryService的search方法即可，四个参数分别对应索引，文档类型(非必须)，queryBody和查询文档结果对应的java bean类型。反序列的结果存放在QueryResponse的results属性下。由于使用了rxjava，可以灵活的使用操作符进行结果流操作和线程切换操作。

### others
1. 注意你的elk版本，不同的elk版本由于rest端点的不同导致部分功能异常，本项目基于elastic serach 5.6版本开发。
2. 客户端支持多节点的elk，但配置时推荐将查询主节点和响应性高的节点配置在非主节点和低响应性的节点之前，这样可以提高部分性能。
