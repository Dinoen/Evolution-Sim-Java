import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.*;
import processing.core.PApplet;

import javax.sound.midi.Soundbank;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GraphingTheData extends Application {

    PApplet p = new PApplet();
    public static boolean openGraphWhenMouse;

    //creating an XYCHART that is called data
    XYChart.Series<Number, Number> data;
    //creating the ScheduledExecutorService
    private ScheduledExecutorService scheduledExecutorService;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //initializing the primary stage function
        openGraphWhenMouse = false;
        init(primaryStage);
        //init1(primaryStage);
        rabbitPopulationSize();
    }

    private void init(Stage primaryStage) {
        HBox root = new HBox();
        //The scene has its root in the HBox
        Scene scene = new Scene(root, 450, 330);

        //Creating two numberAxis x and y. These are animated and the are labelt
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Time");
        xAxis.setAnimated(true);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Average Of Rabbit Speed Genes");
        yAxis.setAnimated(true);

        //Creating our linechart and setting the title
        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Graph of Data");

        //initializing the data XYChart
        data = new XYChart.Series<>();

        //feeding the lineChart the XYChart called data. Providing the
        lineChart.getData().add(data);
        lineChart.setAnimated(true);
        root.getChildren().add(lineChart);

        primaryStage.setTitle("First Graph");
        primaryStage.setScene(scene);
        primaryStage.show();

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            //here we are sending the data to a que that runsLater or runs when it it supposed to.
            Platform.runLater(() -> {
                data.getData().add(new XYChart.Data<Number, Number>(returnMillisecSinceBeginning(), getTheAverageSpeedGene()));
            });
        }, 0, 1, TimeUnit.SECONDS);

        //rabbitPopulationSize();
        //sequentially returning a task object, and we are using fixed rate
    }

    private void init1(Stage primaryStage) {
        HBox root = new HBox();
        Scene scene = new Scene(root,600,600);

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Time");
        xAxis.setAnimated(true);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("The Overall Population");
        yAxis.setAnimated(true);

        //Creating our linechart and setting the title
        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Graph of Data");

        //initializing the data XYChart
        data = new XYChart.Series<>();

        lineChart.getData().add(data);
        lineChart.setAnimated(true);
        root.getChildren().add(lineChart);

        primaryStage.setTitle("First Graph");
        primaryStage.setScene(scene);
        primaryStage.show();

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            //here we are sending the data to a que that runsLater or runs when it it supposed to.
            Platform.runLater(() -> {
                data.getData().add(new XYChart.Data<Number, Number>(returnMillisecSinceBeginning(), rabbitPopulationSize()));
            });
        }, 0, 1, TimeUnit.SECONDS);

    }

    public int rabbitPopulationSize() {

        int theRabbitPopulation = 0;

        for (int i = 0; i < Main.allEntities.get(0).arrayOfEntities.size(); i++) {
            if(Main.allEntities.get(0).arrayOfEntities.size() != 0) {
                theRabbitPopulation = Main.allEntities.get(0).arrayOfEntities.size();
            }
        }

        return theRabbitPopulation;
    }

    //we return and int that is the poopulation size of the rabbits
    public float getTheAverageSpeedGene() {
        float amount = 0;
        float theSpeedGenesAverage;


        for (int i = 0; i < Main.allEntities.get(0).getEntities().size(); i++) {
            if (Main.allEntities.get(0).getEntities().get(i) != null) {
                amount = amount+ Main.allEntities.get(0).getEntities().get(i).movementSpeed;
            }
        }
        theSpeedGenesAverage = amount / Main.allEntities.get(0).getEntities().size();
        return theSpeedGenesAverage;
    }

    //returning the time since the program has started
    public int returnMillisecSinceBeginning() {
        int theTimeInMinutes = (p.millis() / 1000);
        return theTimeInMinutes;
    }
}
