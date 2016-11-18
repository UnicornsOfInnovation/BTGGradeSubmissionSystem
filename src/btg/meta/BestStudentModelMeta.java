package btg.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2016-11-18 16:42:46")
/** */
public final class BestStudentModelMeta extends org.slim3.datastore.ModelMeta<btg.model.BestStudentModel> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.BestStudentModel, java.lang.Long> accountId = new org.slim3.datastore.CoreAttributeMeta<btg.model.BestStudentModel, java.lang.Long>(this, "accountId", "accountId", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.BestStudentModel, java.lang.Long> bestStudentId = new org.slim3.datastore.CoreAttributeMeta<btg.model.BestStudentModel, java.lang.Long>(this, "bestStudentId", "bestStudentId", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.BestStudentModel, java.lang.Long> courseId = new org.slim3.datastore.CoreAttributeMeta<btg.model.BestStudentModel, java.lang.Long>(this, "courseId", "courseId", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.BestStudentModel> courseName = new org.slim3.datastore.StringAttributeMeta<btg.model.BestStudentModel>(this, "courseName", "courseName");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.BestStudentModel> firstName = new org.slim3.datastore.StringAttributeMeta<btg.model.BestStudentModel>(this, "firstName", "firstName");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.BestStudentModel, java.lang.Double> grade = new org.slim3.datastore.CoreAttributeMeta<btg.model.BestStudentModel, java.lang.Double>(this, "grade", "grade", java.lang.Double.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.BestStudentModel, java.lang.Long> gradeId = new org.slim3.datastore.CoreAttributeMeta<btg.model.BestStudentModel, java.lang.Long>(this, "gradeId", "gradeId", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.BestStudentModel, java.lang.Long> id = new org.slim3.datastore.CoreAttributeMeta<btg.model.BestStudentModel, java.lang.Long>(this, "id", "id", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.BestStudentModel, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<btg.model.BestStudentModel, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.BestStudentModel> lastName = new org.slim3.datastore.StringAttributeMeta<btg.model.BestStudentModel>(this, "lastName", "lastName");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.BestStudentModel, java.lang.Boolean> status = new org.slim3.datastore.CoreAttributeMeta<btg.model.BestStudentModel, java.lang.Boolean>(this, "status", "status", boolean.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.BestStudentModel, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<btg.model.BestStudentModel, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final BestStudentModelMeta slim3_singleton = new BestStudentModelMeta();

    /**
     * @return the singleton
     */
    public static BestStudentModelMeta get() {
       return slim3_singleton;
    }

    /** */
    public BestStudentModelMeta() {
        super("BestStudentModel", btg.model.BestStudentModel.class);
    }

    @Override
    public btg.model.BestStudentModel entityToModel(com.google.appengine.api.datastore.Entity entity) {
        btg.model.BestStudentModel model = new btg.model.BestStudentModel();
        model.setAccountId((java.lang.Long) entity.getProperty("accountId"));
        model.setBestStudentId((java.lang.Long) entity.getProperty("bestStudentId"));
        model.setCourseId((java.lang.Long) entity.getProperty("courseId"));
        model.setCourseName((java.lang.String) entity.getProperty("courseName"));
        model.setFirstName((java.lang.String) entity.getProperty("firstName"));
        model.setGrade((java.lang.Double) entity.getProperty("grade"));
        model.setGradeId((java.lang.Long) entity.getProperty("gradeId"));
        model.setId((java.lang.Long) entity.getProperty("id"));
        model.setKey(entity.getKey());
        model.setLastName((java.lang.String) entity.getProperty("lastName"));
        model.setStatus(booleanToPrimitiveBoolean((java.lang.Boolean) entity.getProperty("status")));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        btg.model.BestStudentModel m = (btg.model.BestStudentModel) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("accountId", m.getAccountId());
        entity.setProperty("bestStudentId", m.getBestStudentId());
        entity.setProperty("courseId", m.getCourseId());
        entity.setProperty("courseName", m.getCourseName());
        entity.setProperty("firstName", m.getFirstName());
        entity.setProperty("grade", m.getGrade());
        entity.setProperty("gradeId", m.getGradeId());
        entity.setProperty("id", m.getId());
        entity.setProperty("lastName", m.getLastName());
        entity.setProperty("status", m.getStatus());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        btg.model.BestStudentModel m = (btg.model.BestStudentModel) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        btg.model.BestStudentModel m = (btg.model.BestStudentModel) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        btg.model.BestStudentModel m = (btg.model.BestStudentModel) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        btg.model.BestStudentModel m = (btg.model.BestStudentModel) model;
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
        btg.model.BestStudentModel m = (btg.model.BestStudentModel) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getAccountId() != null){
            writer.setNextPropertyName("accountId");
            encoder0.encode(writer, m.getAccountId());
        }
        if(m.getBestStudentId() != null){
            writer.setNextPropertyName("bestStudentId");
            encoder0.encode(writer, m.getBestStudentId());
        }
        if(m.getCourseId() != null){
            writer.setNextPropertyName("courseId");
            encoder0.encode(writer, m.getCourseId());
        }
        if(m.getCourseName() != null){
            writer.setNextPropertyName("courseName");
            encoder0.encode(writer, m.getCourseName());
        }
        if(m.getFirstName() != null){
            writer.setNextPropertyName("firstName");
            encoder0.encode(writer, m.getFirstName());
        }
        if(m.getGrade() != null){
            writer.setNextPropertyName("grade");
            encoder0.encode(writer, m.getGrade());
        }
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
        if(m.getLastName() != null){
            writer.setNextPropertyName("lastName");
            encoder0.encode(writer, m.getLastName());
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
    protected btg.model.BestStudentModel jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        btg.model.BestStudentModel m = new btg.model.BestStudentModel();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("accountId");
        m.setAccountId(decoder0.decode(reader, m.getAccountId()));
        reader = rootReader.newObjectReader("bestStudentId");
        m.setBestStudentId(decoder0.decode(reader, m.getBestStudentId()));
        reader = rootReader.newObjectReader("courseId");
        m.setCourseId(decoder0.decode(reader, m.getCourseId()));
        reader = rootReader.newObjectReader("courseName");
        m.setCourseName(decoder0.decode(reader, m.getCourseName()));
        reader = rootReader.newObjectReader("firstName");
        m.setFirstName(decoder0.decode(reader, m.getFirstName()));
        reader = rootReader.newObjectReader("grade");
        m.setGrade(decoder0.decode(reader, m.getGrade()));
        reader = rootReader.newObjectReader("gradeId");
        m.setGradeId(decoder0.decode(reader, m.getGradeId()));
        reader = rootReader.newObjectReader("id");
        m.setId(decoder0.decode(reader, m.getId()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("lastName");
        m.setLastName(decoder0.decode(reader, m.getLastName()));
        reader = rootReader.newObjectReader("status");
        m.setStatus(decoder0.decode(reader, m.getStatus()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}