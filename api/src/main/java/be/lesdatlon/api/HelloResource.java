package be.lesdatlon.api;

import be.lesdatlon.api.config.Database;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public Response hello() {
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM DUAL");
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next())
                return Response.ok("OK").build();

            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("DOWN").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("SQL EXCEPTION").build();
        }
    }
}