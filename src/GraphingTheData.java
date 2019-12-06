import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    Stage window;
    Scene scene1, scene2;

    //creating an XYCHART that is called data
    XYChart.Series<Number, Number> data;
    XYChart.Series<Number, Number> data1;
    //creating the ScheduledExecutorService
    private ScheduledExecutorService scheduledExecutorService;
    private ScheduledExecutorService scheduledExecutorService1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        init(primaryStage);
        //initializing the primary stage function
        openGraphWhenMouse = false;
    }

    private void init(Stage primaryStage) {

        window = primaryStage;
        HBox root = new HBox();
        scene2 = new Scene(root, 450, 330);

        window.setScene(scene2);

        Button button2 = new Button("Go to speed gene graph");
        button2.setOnAction(e -> window.close());
        button2.setOnAction(e -> window.setScene(scene2));


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


        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            //here we are sending the data to a que that runsLater or runs when it it supposed to.
            Platform.runLater(() -> {
                data.getData().add(new XYChart.Data<Number, Number>(returnMillisecSinceBeginning(), getTheAverageSpeedGene()));
            });
        }, 0, 1, TimeUnit.SECONDS);

        //rabbitPopulationSize();
        //sequentially returning a task object, and we are using fixed rate

        HBox root1 = new HBox();
        Scene scene1 = new Scene(root1,450,330);

        Button button1 = new Button("Go to populationSize");
        button1.setOnAction(e -> window.close());
        button1.setOnAction(e -> window.setScene(scene1));
        root.getChildren().addAll(button1);
        root1.getChildren().addAll(button2);

        NumberAxis xAxis1 = new NumberAxis();
        xAxis1.setLabel("Time");
        xAxis1.setAnimated(true);

        NumberAxis yAxis1 = new NumberAxis();
        yAxis1.setLabel("The Overall Population");
        yAxis1.setAnimated(true);

        //Creating our linechart and setting the title
        LineChart<Number, Number> lineChart1 = new LineChart<Number, Number>(xAxis1, yAxis1);
        lineChart1.setTitle("Graph of Data");

        //initializing the data XYChart
        data1 = new XYChart.Series<>();

        lineChart1.getData().add(data1);
        lineChart1.setAnimated(true);
        root1.getChildren().add(lineChart1);


        scheduledExecutorService1 = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService1.scheduleAtFixedRate(() -> {
            //here we are sending the data to a que that runsLater or runs when it it supposed to.
            Platform.runLater(() -> {
                data1.getData().add(new XYChart.Data<Number, Number>(returnMillisecSinceBeginning(), rabbitPopulationSize()));
            });
        }, 0, 1, TimeUnit.SECONDS);

        window.show();
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
