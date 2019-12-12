import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import processing.core.PApplet;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class createGraph {

    PApplet p = new PApplet();
    XYChart.Series<Number, Number> data;
    HBox root;
    NumberAxis xAxis;
    NumberAxis yAxis;
    LineChart<Number, Number> lineChart;
    private ScheduledExecutorService scheduledExecutorService;
    private ScheduledExecutorService scheduledExecutorService1;
    private String nameOfGraph;

    createGraph(String value) {

        root = new HBox();
        xAxis = new NumberAxis();
        xAxis.setLabel("Time");
        xAxis.setAnimated(true);

        yAxis = new NumberAxis();
        yAxis.setLabel(this.nameOfGraph = value);
        yAxis.setAnimated(true);

        //Creating our linechart and setting the title
        lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Graph of Data");

        //initializing the data XYChart
        data = new XYChart.Series<>();

        //feeding the lineChart the XYChart called data. Providing the
        lineChart.getData().add(data);
        lineChart.setAnimated(true);
        root.getChildren().add(lineChart);
    }

    public void scheduledExecutorService() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            //here we are sending the data to a que that runsLater or runs when it it supposed to.
            Platform.runLater(() -> {
                data.getData().add(new XYChart.Data<Number, Number>(returnMillisecSinceBeginning(), getTheAverageSpeedGene()));
            });
        }, 0, 1, TimeUnit.SECONDS);
    }

    public void scheduledExecutorService1() {
        scheduledExecutorService1 = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService1.scheduleAtFixedRate(() -> {
            //here we are sending the data to a que that runsLater or runs when it it supposed to.
            Platform.runLater(() -> {
                data.getData().add(new XYChart.Data<Number, Number>(returnMillisecSinceBeginning(), rabbitPopulationSize()));
            });
        }, 0, 1, TimeUnit.SECONDS);
    }


    public int returnMillisecSinceBeginning() {
        int theTimeInMinutes = (p.millis() / 1000);
        return theTimeInMinutes;
    }

    public float getTheAverageSpeedGene() {
        float amount = 0;
        float theSpeedGenesAverage;


        for (int i = 0; i < Main.allEntities.get(0).getEntities().size(); i++) {
            if (Main.allEntities.get(0).getEntities().get(i) != null) {
                amount = amount + (((Animal)Main.allEntities.get(0).arrayOfEntities.get(i)).getMovementSpeed());
            }
        }
        theSpeedGenesAverage = amount / Main.allEntities.get(0).getEntities().size();
        return theSpeedGenesAverage;
    }

    public int rabbitPopulationSize() {

        int theRabbitPopulation = 0;

        for (int i = 0; i < Main.allEntities.get(0).arrayOfEntities.size(); i++) {
            if (Main.allEntities.get(0).arrayOfEntities.size() != 0) {
                theRabbitPopulation = Main.allEntities.get(0).arrayOfEntities.size();
            }
        }

        return theRabbitPopulation;
    }
}
