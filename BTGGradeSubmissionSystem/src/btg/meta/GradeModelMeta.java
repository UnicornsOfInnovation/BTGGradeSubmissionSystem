package btg.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2016-11-07 18:45:16")
/** */
public final class GradeModelMeta extends org.slim3.datastore.ModelMeta<btg.model.GradeModel> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.GradeModel, java.lang.Long> accountId = new org.slim3.datastore.CoreAttributeMeta<btg.model.GradeModel, java.lang.Long>(this, "accountId", "accountId", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.GradeModel, java.lang.Long> courseId = new org.slim3.datastore.CoreAttributeMeta<btg.model.GradeModel, java.lang.Long>(this, "courseId", "courseId", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.GradeModel, java.lang.Double> grade = new org.slim3.datastore.CoreAttributeMeta<btg.model.GradeModel, java.lang.Double>(this, "grade", "grade", double.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.GradeModel, java.lang.Long> gradeId = new org.slim3.datastore.CoreAttributeMeta<btg.model.GradeModel, java.lang.Long>(this, "gradeId", "gradeId", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.GradeModel, java.lang.Long> id = new org.slim3.datastore.CoreAttributeMeta<btg.model.GradeModel, java.lang.Long>(this, "id", "id", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.GradeModel, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<btg.model.GradeModel, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.GradeModel, java.lang.Boolean> status = new org.slim3.datastore.CoreAttributeMeta<btg.model.GradeModel, java.lang.Boolean>(this, "status", "status", boolean.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.GradeModel, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<btg.model.GradeModel, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final GradeModelMeta slim3_singleton = new GradeModelMeta();

    /**
     * @return the singleton
     */
    public static GradeModelMeta get() {
       return slim3_singleton;
    }

    /** */
    public GradeModelMeta() {
        super("GradeModel", btg.model.GradeModel.class);
    }

    @Override
    public btg.model.GradeModel entityToModel(com.google.appengine.api.datastore.Entity entity) {
        btg.model.GradeModel model = new btg.model.GradeModel();
        model.setAccountId((java.lang.Long) entity.getProperty("accountId"));
        model.setCourseId((java.lang.Long) entity.getProperty("courseId"));
        model.setGrade(doubleToPrimitiveDouble((java.lang.Double) entity.getProperty("grade")));
        model.setGradeId((java.lang.Long) entity.getProperty("gradeId"));
        model.setId((java.lang.Long) entity.getProperty("id"));
        model.setKey(entity.getKey());
        model.setStatus(booleanToPrimitiveBoolean((java.lang.Boolean) entity.getProperty("status")));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        btg.model.GradeModel m = (btg.model.GradeModel) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("accountId", m.getAccountId());
        entity.setProperty("courseId", m.getCourseId());
        entity.setProperty("grade", m.getGrade());
        entity.setProperty("gradeId", m.getGradeId());
        entity.setProperty("id", m.getId());
        entity.setProperty("status", m.getStatus());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        btg.model.GradeModel m = (btg.model.GradeModel) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        btg.model.GradeModel m = (btg.model.GradeModel) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        btg.model.GradeModel m = (btg.model.GradeModel) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        btg.model.GradeModel m = (btg.model.GradeModel) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        btg.model.GradeModel m = (btg.model.GradeModel) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getAccountId() != null){
            writer.setNextPropertyName("accountId");
            encoder0.encode(writer, m.getAccountId());
        }
        if(m.getCourseId() != null){
            writer.setNextPropertyName("courseId");
            encoder0.encode(writer, m.getCourseId());
        }
        writer.setNextPropertyName("grade");
        encoder0.encode(writer, m.getGrade());
        if(m.getGradeId() != null){
            writer.setNextPropertyName("gradeId");
            encoder0.encode(writer, m.getGradeId());
        }
        if(m.getId() != null){
            writer.setNextPropertyName("id");
            encoder0.encode(writer, m.getId());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        writer.setNextPropertyName("status");
        encoder0.encode(writer, m.getStatus());
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected btg.model.GradeModel jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        btg.model.GradeModel m = new btg.model.GradeModel();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("accountId");
        m.setAccountId(decoder0.decode(reader, m.getAccountId()));
        reader = rootReader.newObjectReader("courseId");
        m.setCourseId(decoder0.decode(reader, m.getCourseId()));
        reader = rootReader.newObjectReader("grade");
        m.setGrade(decoder0.decode(reader, m.getGrade()));
        reader = rootReader.newObjectReader("gradeId");
        m.setGradeId(decoder0.decode(reader, m.getGradeId()));
        reader = rootReader.newObjectReader("id");
        m.setId(decoder0.decode(reader, m.getId()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("status");
        m.setStatus(decoder0.decode(reader, m.getStatus()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}