package btg.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2017-01-02 14:00:30")
/** */
public final class CourseModelMeta extends org.slim3.datastore.ModelMeta<btg.model.CourseModel> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.CourseModel> courseCode = new org.slim3.datastore.StringAttributeMeta<btg.model.CourseModel>(this, "courseCode", "courseCode");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.CourseModel, java.lang.Long> courseId = new org.slim3.datastore.CoreAttributeMeta<btg.model.CourseModel, java.lang.Long>(this, "courseId", "courseId", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.CourseModel> courseName = new org.slim3.datastore.StringAttributeMeta<btg.model.CourseModel>(this, "courseName", "courseName");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.CourseModel> courseType = new org.slim3.datastore.StringAttributeMeta<btg.model.CourseModel>(this, "courseType", "courseType");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.CourseModel, java.lang.Integer> courseUnits = new org.slim3.datastore.CoreAttributeMeta<btg.model.CourseModel, java.lang.Integer>(this, "courseUnits", "courseUnits", int.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.CourseModel, java.lang.Long> id = new org.slim3.datastore.CoreAttributeMeta<btg.model.CourseModel, java.lang.Long>(this, "id", "id", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.CourseModel, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<btg.model.CourseModel, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.CourseModel, java.lang.Boolean> status = new org.slim3.datastore.CoreAttributeMeta<btg.model.CourseModel, java.lang.Boolean>(this, "status", "status", boolean.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.CourseModel> strand = new org.slim3.datastore.StringAttributeMeta<btg.model.CourseModel>(this, "strand", "strand");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.CourseModel, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<btg.model.CourseModel, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.CourseModel, java.lang.Integer> yearLevel = new org.slim3.datastore.CoreAttributeMeta<btg.model.CourseModel, java.lang.Integer>(this, "yearLevel", "yearLevel", int.class);

    private static final CourseModelMeta slim3_singleton = new CourseModelMeta();

    /**
     * @return the singleton
     */
    public static CourseModelMeta get() {
       return slim3_singleton;
    }

    /** */
    public CourseModelMeta() {
        super("CourseModel", btg.model.CourseModel.class);
    }

    @Override
    public btg.model.CourseModel entityToModel(com.google.appengine.api.datastore.Entity entity) {
        btg.model.CourseModel model = new btg.model.CourseModel();
        model.setCourseCode((java.lang.String) entity.getProperty("courseCode"));
        model.setCourseId((java.lang.Long) entity.getProperty("courseId"));
        model.setCourseName((java.lang.String) entity.getProperty("courseName"));
        model.setCourseType((java.lang.String) entity.getProperty("courseType"));
        model.setCourseUnits(longToPrimitiveInt((java.lang.Long) entity.getProperty("courseUnits")));
        model.setId((java.lang.Long) entity.getProperty("id"));
        model.setKey(entity.getKey());
        model.setStatus(booleanToPrimitiveBoolean((java.lang.Boolean) entity.getProperty("status")));
        model.setStrand((java.lang.String) entity.getProperty("strand"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        model.setYearLevel(longToPrimitiveInt((java.lang.Long) entity.getProperty("yearLevel")));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        btg.model.CourseModel m = (btg.model.CourseModel) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("courseCode", m.getCourseCode());
        entity.setProperty("courseId", m.getCourseId());
        entity.setProperty("courseName", m.getCourseName());
        entity.setProperty("courseType", m.getCourseType());
        entity.setProperty("courseUnits", m.getCourseUnits());
        entity.setProperty("id", m.getId());
        entity.setProperty("status", m.getStatus());
        entity.setProperty("strand", m.getStrand());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("yearLevel", m.getYearLevel());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        btg.model.CourseModel m = (btg.model.CourseModel) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        btg.model.CourseModel m = (btg.model.CourseModel) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        btg.model.CourseModel m = (btg.model.CourseModel) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        btg.model.CourseModel m = (btg.model.CourseModel) model;
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
        btg.model.CourseModel m = (btg.model.CourseModel) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getCourseCode() != null){
            writer.setNextPropertyName("courseCode");
            encoder0.encode(writer, m.getCourseCode());
        }
        if(m.getCourseId() != null){
            writer.setNextPropertyName("courseId");
            encoder0.encode(writer, m.getCourseId());
        }
        if(m.getCourseName() != null){
            writer.setNextPropertyName("courseName");
            encoder0.encode(writer, m.getCourseName());
        }
        if(m.getCourseType() != null){
            writer.setNextPropertyName("courseType");
            encoder0.encode(writer, m.getCourseType());
        }
        writer.setNextPropertyName("courseUnits");
        encoder0.encode(writer, m.getCourseUnits());
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
        if(m.getStrand() != null){
            writer.setNextPropertyName("strand");
            encoder0.encode(writer, m.getStrand());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.setNextPropertyName("yearLevel");
        encoder0.encode(writer, m.getYearLevel());
        writer.endObject();
    }

    @Override
    protected btg.model.CourseModel jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        btg.model.CourseModel m = new btg.model.CourseModel();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("courseCode");
        m.setCourseCode(decoder0.decode(reader, m.getCourseCode()));
        reader = rootReader.newObjectReader("courseId");
        m.setCourseId(decoder0.decode(reader, m.getCourseId()));
        reader = rootReader.newObjectReader("courseName");
        m.setCourseName(decoder0.decode(reader, m.getCourseName()));
        reader = rootReader.newObjectReader("courseType");
        m.setCourseType(decoder0.decode(reader, m.getCourseType()));
        reader = rootReader.newObjectReader("courseUnits");
        m.setCourseUnits(decoder0.decode(reader, m.getCourseUnits()));
        reader = rootReader.newObjectReader("id");
        m.setId(decoder0.decode(reader, m.getId()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("status");
        m.setStatus(decoder0.decode(reader, m.getStatus()));
        reader = rootReader.newObjectReader("strand");
        m.setStrand(decoder0.decode(reader, m.getStrand()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        reader = rootReader.newObjectReader("yearLevel");
        m.setYearLevel(decoder0.decode(reader, m.getYearLevel()));
        return m;
    }
}