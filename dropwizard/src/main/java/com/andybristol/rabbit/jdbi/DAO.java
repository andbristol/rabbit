package com.andybristol.rabbit.jdbi;

import com.andybristol.rabbit.api.Bun;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DAO {

    @SqlUpdate("INSERT INTO buns (name, power_level) VALUES (:name, :power_level)")
    @GetGeneratedKeys
    public int createBun(@Bind("name") String name, @Bind("power_level") int powerLevel);

    @SqlQuery("SELECT * FROM buns WHERE id = :id")
    @Mapper(BunMapper.class)
    public Bun getBun(@Bind("id") int id);

    static class BunMapper implements ResultSetMapper<Bun> {

        @Override
        public Bun map(int index, ResultSet r, StatementContext ctx) throws SQLException {
            return new Bun(r.getInt("id"), r.getString("name"), r.getInt("power_level"));
        }
    }

    @SqlUpdate("UPDATE buns SET name = :name, power_level = :power_level WHERE id = :id")
    public int updateBun(@Bind("id") int id, @Bind("name") String name, @Bind("power_level") int powerLevel);

    @SqlUpdate("DELETE FROM buns where id = :id")
    public int deleteBun(@Bind("id") int id);
}
