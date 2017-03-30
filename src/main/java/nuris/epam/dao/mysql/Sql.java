package nuris.epam.dao.mysql;

/**
 * Created by User on 12.03.2017.
 */
public class Sql {

    private final StringBuilder stringBuilder = new StringBuilder();

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public void setStringBuilder(String str) {
        this.stringBuilder.append(str);
    }

    private Sql() {

    }

    public static Builder create() {
        return new Sql().new Builder();
    }

    public class Builder {

        private Builder() {

        }

        public Builder select() {
            Sql.this.setStringBuilder("select ");
            return this;
        }

        public Builder update() {
            Sql.this.setStringBuilder("update ");
            return this;
        }

        public Builder set() {
            Sql.this.setStringBuilder("set ");
            return this;
        }

        public Builder from() {
            Sql.this.setStringBuilder("from ");
            return this;
        }

        public Builder delete() {
            Sql.this.setStringBuilder("delete from ");
            return this;
        }

        public Builder count() {
            Sql.this.setStringBuilder("count(*) ");
            return this;
        }

        public Builder all() {
            Sql.this.setStringBuilder("*");
            return this;
        }

        public Builder where(String str) {
            Sql.this.setStringBuilder("where " + str + " = ");
            return this;
        }

        public Builder whereQs(String str) {
            Sql.this.setStringBuilder(" where " + str + " = ? ");
            return this;
        }

        public Builder whereQs(String table, String column) {
            Sql.this.setStringBuilder(" where " + table + "." + column + " = ? ");
            return this;
        }

        public Builder eq() {
            Sql.this.setStringBuilder(" = ");
            return this;
        }

        public Builder eq(String s) {
            Sql.this.setStringBuilder(" = " + s);
            return this;
        }

        public Builder qs() {
            Sql.this.setStringBuilder("?");
            return this;
        }

        public Builder eqqs() {
            Sql.this.setStringBuilder("=?");
            return this;
        }

        public Builder var(String s) {
            Sql.this.setStringBuilder(s + " ");
            return this;
        }

        public Builder varQs(String column) {
            Sql.this.setStringBuilder(column + " = ?");
            return this;
        }

        public Builder join(String table) {
            Sql.this.setStringBuilder("join " + table + " on ");
            return this;
        }

        public Builder varS(String table, String column) {
            Sql.this.setStringBuilder(table + "." + column + " ");
            return this;
        }

        public Builder c() {
            Sql.this.setStringBuilder(",");
            return this;
        }

        public Builder limit() {
            Sql.this.setStringBuilder("limit ?,? ");
            return this;
        }

        public Builder allFrom() {
            Sql.this.setStringBuilder("* from ");
            return this;
        }

        public Builder insert() {
            Sql.this.setStringBuilder("insert into ");
            return this;
        }

        public Builder values(String var, int count) {
            Sql.this.setStringBuilder("values(" + var + ",");
            questionCount(count);
            Sql.this.setStringBuilder(")");
            return this;
        }

        public Builder valuesNull(String var, int count) {
            Sql.this.setStringBuilder("values(" + var + ",");
            questionCount(count);
            Sql.this.setStringBuilder(",null)");
            return this;
        }

        public Builder and(){
            Sql.this.setStringBuilder(" and ");
            return this;
        }

        private void questionCount(int count) {
            for (int i = 1; i <= count; i++) {
                if (i < count) {
                    Sql.this.setStringBuilder("?");
                    Sql.this.setStringBuilder(",");
                } else {
                    Sql.this.setStringBuilder("?");
                }
            }
        }



        public String build() {
            return Sql.this.getStringBuilder().toString();
        }


    }

}