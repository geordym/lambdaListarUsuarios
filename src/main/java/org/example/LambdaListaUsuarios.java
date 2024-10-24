package org.example;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class LambdaListaUsuarios implements RequestHandler<Input, Output>
{
    private final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    private final DynamoDB dynamoDB = new DynamoDB(client);
    private final String tableName = "usuarios_tabla";

    @Override
    public Output handleRequest(Input input, Context context) {
        List<Usuario> usuarioList = new ArrayList<>();

        ScanRequest scanRequest = new ScanRequest()
                .withTableName(tableName);
        ScanResult result = client.scan(scanRequest);

        for (Map<String, AttributeValue> item : result.getItems()) {
            String id = item.get("id").getS();
            String telefono = item.get("telefono").getS();
            String nombre = item.get("nombre").getS();
            String correo = item.get("email").getS();

            Usuario usuario = new Usuario(id, telefono, nombre, correo);
            usuarioList.add(usuario);
        }

        return new Output(usuarioList);
    }
}
