package btg.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2016-11-11 15:33:57")
/** */
public final class AccountModelMeta extends org.slim3.datastore.ModelMeta<btg.model.AccountModel> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.AccountModel, java.lang.Long> accountId = new org.slim3.datastore.CoreAttributeMeta<btg.model.AccountModel, java.lang.Long>(this, "accountId", "accountId", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel> contactNumber = new org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel>(this, "contactNumber", "contactNumber");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel> courseCode = new org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel>(this, "courseCode", "courseCode");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel> emailAddress = new org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel>(this, "emailAddress", "emailAddress");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel> firstName = new org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel>(this, "firstName", "firstName");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.AccountModel, java.lang.Double> gpa = new org.slim3.datastore.CoreAttributeMeta<btg.model.AccountModel, java.lang.Double>(this, "gpa", "gpa", double.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.AccountModel, java.lang.Long> id = new org.slim3.datastore.CoreAttributeMeta<btg.model.AccountModel, java.lang.Long>(this, "id", "id", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.AccountModel, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<btg.model.AccountModel, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel> lastName = new org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel>(this, "lastName", "lastName");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel> parentContact = new org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel>(this, "parentContact", "parentContact");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel> parentName = new org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel>(this, "parentName", "parentName");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel> password = new org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel>(this, "password", "password");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel> school = new org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel>(this, "school", "school");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.AccountModel, java.lang.Boolean> status = new org.slim3.datastore.CoreAttributeMeta<btg.model.AccountModel, java.lang.Boolean>(this, "status", "status", boolean.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel> strand = new org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel>(this, "strand", "strand");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel> userType = new org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel>(this, "userType", "userType");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel> username = new org.slim3.datastore.StringAttributeMeta<btg.model.AccountModel>(this, "username", "username");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.AccountModel, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<btg.model.AccountModel, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<btg.model.AccountModel, java.lang.Integer> yearLevel = new org.slim3.datastore.CoreAttributeMeta<btg.model.AccountModel, java.lang.Integer>(this, "yearLevel", "yearLevel", int.class);

    private static final AccountModelMeta slim3_singleton = new AccountModelMeta();

    /**
     * @return the singleton
     */
    public static AccountModelMeta get() {
       return slim3_singleton;
    }

    /** */
    public AccountModelMeta() {
        super("AccountModel", btg.model.AccountModel.class);
    }

    @Override
    public btg.model.AccountModel entityToModel(com.google.appengine.api.datastore.Entity entity) {
        btg.model.AccountModel model = new btg.model.AccountModel();
        model.setAccountId((java.lang.Long) entity.getProperty("accountId"));
        model.setContactNumber((java.lang.String) entity.getProperty("contactNumber"));
        model.setCourseCode((java.lang.String) entity.getProperty("courseCode"));
        model.setEmailAddress((java.lang.String) entity.getProperty("emailAddress"));
        model.setFirstName((java.lang.String) entity.getProperty("firstName"));
        model.setGpa(doubleToPrimitiveDouble((java.lang.Double) entity.getProperty("gpa")));
        model.setId((java.lang.Long) entity.getProperty("id"));
        model.setKey(entity.getKey());
        model.setLastName((java.lang.String) entity.getProperty("lastName"));
        model.setParentContact((java.lang.String) entity.getProperty("parentContact"));
        model.setParentName((java.lang.String) entity.getProperty("parentName"));
        model.setPassword((java.lang.String) entity.getProperty("password"));
        model.setSchool((java.lang.String) entity.getProperty("school"));
        model.setStatus(booleanToPrimitiveBoolean((java.lang.Boolean) entity.getProperty("status")));
        model.setStrand((java.lang.String) entity.getProperty("strand"));
        model.setUserType((java.lang.String) entity.getProperty("userType"));
        model.setUsername((java.lang.String) entity.getProperty("username"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        model.setYearLevel(longToPrimitiveInt((java.lang.Long) entity.getProperty("yearLevel")));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        btg.model.AccountModel m = (btg.model.AccountModel) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("accountId", m.getAccountId());
        entity.setProperty("contactNumber", m.getContactNumber());
        entity.setProperty("courseCode", m.getCourseCode());
        entity.setProperty("emailAddress", m.getEmailAddress());
        entity.setProperty("firstName", m.getFirstName());
        entity.setProperty("gpa", m.getGpa());
        entity.setProperty("id", m.getId());
        entity.setProperty("lastName", m.getLastName());
        entity.setProperty("parentContact", m.getParentContact());
        entity.setProperty("parentName", m.getParentName());
        entity.setProperty("password", m.getPassword());
        entity.setProperty("school", m.getSchool());
        entity.setProperty("status", m.getStatus());
        entity.setProperty("strand", m.getStrand());
        entity.setProperty("userType", m.getUserType());
        entity.setProperty("username", m.getUsername());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("yearLevel", m.getYearLevel());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        btg.model.AccountModel m = (btg.model.AccountModel) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        btg.model.AccountModel m = (btg.model.AccountModel) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        btg.model.AccountModel m = (btg.model.AccountModel) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        btg.model.AccountModel m = (btg.model.AccountModel) model;
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
        btg.model.AccountModel m = (btg.model.AccountModel) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getAccountId() != null){
            writer.setNextPropertyName("accountId");
            encoder0.encode(writer, m.getAccountId());
        }
        if(m.getContactNumber() != null){
            writer.setNextPropertyName("contactNumber");
            encoder0.encode(writer, m.getContactNumber());
        }
        if(m.getCourseCode() != null){
            writer.setNextPropertyName("courseCode");
            encoder0.encode(writer, m.getCourseCode());
        }
        if(m.getEmailAddress() != null){
            writer.setNextPropertyName("emailAddress");
            encoder0.encode(writer, m.getEmailAddress());
        }
        if(m.getFirstName() != null){
            writer.setNextPropertyName("firstName");
            encoder0.encode(writer, m.getFirstName());
        }
        writer.setNextPropertyName("gpa");
        encoder0.encode(writer, m.getGpa());
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
        if(m.getParentContact() != null){
            writer.setNextPropertyName("parentContact");
            encoder0.encode(writer, m.getParentContact());
        }
        if(m.getParentName() != null){
            writer.setNextPropertyName("parentName");
            encoder0.encode(writer, m.getParentName());
        }
        if(m.getPassword() != null){
            writer.setNextPropertyName("password");
            encoder0.encode(writer, m.getPassword());
        }
        if(m.getSchool() != null){
            writer.setNextPropertyName("school");
            encoder0.encode(writer, m.getSchool());
        }
        writer.setNextPropertyName("status");
        encoder0.encode(writer, m.getStatus());
        if(m.getStrand() != null){
            writer.setNextPropertyName("strand");
            encoder0.encode(writer, m.getStrand());
        }
        if(m.getUserType() != null){
            writer.setNextPropertyName("userType");
            encoder0.encode(writer, m.getUserType());
        }
        if(m.getUsername() != null){
            writer.setNextPropertyName("username");
            encoder0.encode(writer, m.getUsername());
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
    protected btg.model.AccountModel jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        btg.model.AccountModel m = new btg.model.AccountModel();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("accountId");
        m.setAccountId(decoder0.decode(reader, m.getAccountId()));
        reader = rootReader.newObjectReader("contactNumber");
        m.setContactNumber(decoder0.decode(reader, m.getContactNumber()));
        reader = rootReader.newObjectReader("courseCode");
        m.setCourseCode(decoder0.decode(reader, m.getCourseCode()));
        reader = rootReader.newObjectReader("emailAddress");
        m.setEmailAddress(decoder0.decode(reader, m.getEmailAddress()));
        reader = rootReader.newObjectReader("firstName");
        m.setFirstName(decoder0.decode(reader, m.getFirstName()));
        reader = rootReader.newObjectReader("gpa");
        m.setGpa(decoder0.decode(reader, m.getGpa()));
        reader = rootReader.newObjectReader("id");
        m.setId(decoder0.decode(reader, m.getId()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("lastName");
        m.setLastName(decoder0.decode(reader, m.getLastName()));
        reader = rootReader.newObjectReader("parentContact");
        m.setParentContact(decoder0.decode(reader, m.getParentContact()));
        reader = rootReader.newObjectReader("parentName");
        m.setParentName(decoder0.decode(reader, m.getParentName()));
        reader = rootReader.newObjectReader("password");
        m.setPassword(decoder0.decode(reader, m.getPassword()));
        reader = rootReader.newObjectReader("school");
        m.setSchool(decoder0.decode(reader, m.getSchool()));
        reader = rootReader.newObjectReader("status");
        m.setStatus(decoder0.decode(reader, m.getStatus()));
        reader = rootReader.newObjectReader("strand");
        m.setStrand(decoder0.decode(reader, m.getStrand()));
        reader = rootReader.newObjectReader("userType");
        m.setUserType(decoder0.decode(reader, m.getUserType()));
        reader = rootReader.newObjectReader("username");
        m.setUsername(decoder0.decode(reader, m.getUsername()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        reader = rootReader.newObjectReader("yearLevel");
        m.setYearLevel(decoder0.decode(reader, m.getYearLevel()));
        return m;
    }
}