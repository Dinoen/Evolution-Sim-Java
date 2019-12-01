import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.*;
import processing.core.PApplet;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GraphingTheData extends Application {

    PApplet p = new PApplet();
    XYChart.Series<Number, Number> data;
    private ScheduledExecutorService scheduledExecutorService;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);

    }

    private void init(Stage primaryStage) {
        HBox root = new HBox();
        Scene scene = new Scene(root, 450, 330);

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Time");
        xAxis.setAnimated(true);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("SpeedGene");
        yAxis.setAnimated(true);

        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("SpeedGenes Over Time");

        data = new XYChart.Series<>();

        //XYChart.Series<Number, Number>
        //data.getData().add(new XYChart.Data<Number,Number>(returnMillisecSinceBeginning(),getTheSpeed()));

        lineChart.getData().add(data);
        lineChart.setAnimated(true);
        root.getChildren().add(lineChart);

        primaryStage.setTitle("Graph For SpeedGenes");
        primaryStage.setScene(scene);
        primaryStage.show();

        //final SimpleDateFormat simpleDataFormat = new SimpleDateFormat("HH:mm:ss");
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {

                data.getData().add(new XYChart.Data<Number, Number>(returnMillisecSinceBeginning(), getTheSpeed()));


            });
        }, 0, 1, TimeUnit.SECONDS);
    }

    public int getTheSpeed() {
        int theActualI;

        theActualI = (int) Main.allEntities.get(0).getEntitiesGrass().get(0).movementSpeed;

        return theActualI;
    }

    public int returnMillisecSinceBeginning() {
        int theTimeInMinutes = (p.millis() / 100);
        return theTimeInMinutes;
    }
}
