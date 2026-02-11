package ticket.booking.services;

import ticket.booking.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TrainService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Train> trainList;
    private static final String TRAINS_PATH = "src/main/java/ticket/booking/localDb/trains.json";

    public List<Train> loadTrains() throws IOException {
        File trains = new File(TRAINS_PATH);
        return objectMapper.readValue(trains, new TypeReference<List<Train>>() {
        });

    }

    public TrainService() throws IOException {
        trainList = loadTrains();
    }

    public List<Train> searchTrains(String source, String destination) {
        // logic to search trains based on source, destination
        List<Train> validTrains = new ArrayList<>();
        for (int i = 0; i < trainList.size(); i++) {
            if (trainList.get(i).getStations().contains(source)
                    && trainList.get(i).getStations().contains(destination)) {
                validTrains.add(trainList.get(i));
                // System.out.println(trainList.get(i).getTrainInfo());
            }
        }
        return validTrains;

    }

    public void addTrain(Train train) throws IOException {
        trainList.add(train);
        saveTrainListToFile();
    }

    private void saveTrainListToFile() throws IOException {
        File trainsFile = new File(TRAINS_PATH);
        objectMapper.writeValue(trainsFile, trainList);
    }
}
