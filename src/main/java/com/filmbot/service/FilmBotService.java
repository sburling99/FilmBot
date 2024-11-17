package com.filmbot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FilmBotService {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
    }

    public JsonNode topThreeFilms(JsonNode arrayNode, int totalResults) {

        List<JsonNode> dataNodes = arrayNode.findParents("popularity");

        List<JsonNode> sortedDataNodes = dataNodes
                .stream()
                .sorted(Comparator.comparingDouble(o -> o.get("popularity").doubleValue()))
                .toList();

        ArrayNode arrayNodes = objectMapper
                .createObjectNode()
                .arrayNode();
        for (int i = totalResults-1; i >= (Math.max(0, totalResults - 3)); i--) {
            arrayNodes.add(sortedDataNodes.get(i));
        }

        return objectMapper.createObjectNode().set("popularity", arrayNodes);
    }
}
