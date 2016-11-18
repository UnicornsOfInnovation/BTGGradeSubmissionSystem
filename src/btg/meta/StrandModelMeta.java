package btg.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2016-11-18 18:43:18")
/** */
public final class StrandModelMeta extends org.slim3.datastore.ModelMeta<btg.model.StrandModel> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.StrandModel, java.lang.Long> id = new org.slim3.datastore.CoreAttributeMeta<btg.model.StrandModel, java.lang.Long>(this, "id", "id", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.StrandModel, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<btg.model.StrandModel, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.StrandModel> strandCode = new org.slim3.datastore.StringAttributeMeta<btg.model.StrandModel>(this, "strandCode", "strandCode");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.StrandModel, java.lang.Long> strandId = new org.slim3.datastore.CoreAttributeMeta<btg.model.StrandModel, java.lang.Long>(this, "strandId", "strandId", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.StrandModel> strandName = new org.slim3.datastore.StringAttributeMeta<btg.model.StrandModel>(this, "strandName", "strandName");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.StrandModel, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<btg.model.StrandModel, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final StrandModelMeta slim3_singleton = new StrandModelMeta();

    /**
     * @return the singleton
     */
    public static StrandModelMeta get() {
       return slim3_singleton;
    }

    /** */
    public StrandModelMeta() {
        super("StrandModel", btg.model.StrandModel.class);
    }

    @Override
    public btg.model.StrandModel entityToModel(com.google.appengine.api.datastore.Entity entity) {
        btg.model.StrandModel model = new btg.model.StrandModel();
        model.setId((java.lang.Long) entity.getProperty("id"));
        model.setKey(entity.getKey());
        model.setStrandCode((java.lang.String) entity.getProperty("strandCode"));
        model.setStrandId((java.lang.Long) entity.getProperty("strandId"));
        model.setStrandName((java.lang.String) entity.getProperty("strandName"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        btg.model.StrandModel m = (btg.model.StrandModel) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("id", m.getId());
        entity.setProperty("strandCode", m.getStrandCode());
        entity.setProperty("strandId", m.getStrandId());
        entity.setProperty("strandName", m.getStrandName());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        btg.model.StrandModel m = (btg.model.StrandModel) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        btg.model.StrandModel m = (btg.model.StrandModel) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        btg.model.StrandModel m = (btg.model.StrandModel) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        btg.model.StrandModel m = (btg.model.StrandModel) model;
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
        btg.model.StrandModel m = (btg.model.StrandModel) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getId() != null){
            writer.setNextPropertyName("id");
            encoder0.encode(writer, m.getId());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getStrandCode() != null){
            writer.setNextPropertyName("strandCode");
            encoder0.encode(writer, m.getStrandCode());
        }
        if(m.getStrandId() != null){
            writer.setNextPropertyName("strandId");
            encoder0.encode(writer, m.getStrandId());
        }
        if(m.getStrandName() != null){
            writer.setNextPropertyName("strandName");
            encoder0.encode(writer, m.getStrandName());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected btg.model.StrandModel jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        btg.model.StrandModel m = new btg.model.StrandModel();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("id");
        m.setId(decoder0.decode(reader, m.getId()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("strandCode");
        m.setStrandCode(decoder0.decode(reader, m.getStrandCode()));
        reader = rootReader.newObjectReader("strandId");
        m.setStrandId(decoder0.decode(reader, m.getStrandId()));
        reader = rootReader.newObjectReader("strandName");
        m.setStrandName(decoder0.decode(reader, m.getStrandName()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}