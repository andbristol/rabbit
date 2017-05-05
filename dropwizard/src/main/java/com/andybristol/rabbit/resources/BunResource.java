package com.andybristol.rabbit.resources;

import com.andybristol.rabbit.api.Bun;
import com.andybristol.rabbit.jdbi.DAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/bun")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BunResource {
    private final static Logger LOG = LoggerFactory.getLogger(BunResource.class);

    private final DAO dao;

    public BunResource(DAO dao) {
        this.dao = dao;
    }

    @GET
    @Path("/{id}")
    public Bun read(@PathParam("id") int id) {
        final Bun bun = dao.getBun(id);
        if (bun == null) {
            // todo don't like throwing exceptions for control flow
            throw new NotFoundException();
        } else {
            return bun;
        }
    }

    @POST
    public CreatedResponse create(@Valid @NotNull Bun bun) {
        final int id = dao.createBun(bun.getName(), bun.getPowerLevel());
        LOG.info("Created bun with id " + id);
        return new CreatedResponse(id);
    }

    class CreatedResponse {
        final public int id;
        public CreatedResponse(int id) {
            this.id = id;
        }
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") int id, @Valid @NotNull Bun bun) {
        LOG.info("Got bun for update: " + bun);
        final int changed = dao.updateBun(id, bun.getName(), bun.getPowerLevel());
        if (changed == 0) {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) {
        final int deleted = dao.deleteBun(id);
        if (deleted == 0) {
            throw new NotFoundException();
        }
    }

}
