import javafx.application.Application;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.*;
import processing.core.PApplet;

public class GraphingTheData extends Application {

    PApplet p = new PApplet();
    public static boolean openGraphWhenMouse;
    Stage window;
    Scene scene1, scene2;

    //creating an XYCHART that is called data
    XYChart.Series<Number, Number> data1;
    //creating the ScheduledExecutorService
    createGraph graph1;
    createGraph graph2;

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
        graph1 = new createGraph();
        graph2 = new createGraph();
        scene2 = new Scene(graph1.root, 450, 330);
        scene1 = new Scene(graph2.root, 450, 330);

        window.setScene(scene2);

        Button button2 = new Button("Go to speed gene graph");
        button2.setOnAction(e -> window.close());
        button2.setOnAction(e -> window.setScene(scene2));

        Button button1 = new Button("Go to populationSize");
        button1.setOnAction(e -> window.close());
        button1.setOnAction(e -> window.setScene(scene1));
        graph1.root.getChildren().addAll(button1);
        graph2.root.getChildren().addAll(button2);

        graph1.scheduledExecutorService();
        graph1.getTheAverageSpeedGene();
        graph1.returnMillisecSinceBeginning();

        graph2.scheduledExecutorService1();
        graph2.rabbitPopulationSize();
        graph2.returnMillisecSinceBeginning();

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

    //we return and int that is the population size of the rabbits
    public float getTheAverageSpeedGene() {
        float amount = 0;
        float theSpeedGenesAverage;


        for (int i = 0; i < Main.allEntities.get(0).getEntities().size(); i++) {
            if (Main.allEntities.get(0).getEntities().get(i) != null) {
                amount = amount+  ((Rabbit) Main.allEntities.get(0).getEntities().get(i)).movementSpeed;
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
