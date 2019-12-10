//import java.util.ArrayList;
//import java.util.Random;
//
//public class LogicTest {
//
//    public enum TestEntityType {
//        RABBIT,FOX,GRASS
//    }
//
//    public enum TestEntityBehaviourType {
//        FEAR,HUNGER,LOVE
//    }
//
//    public class TestEntity {
//
//        public TestEntityType EntityType;
//
//        public TestEntityBehaviourType EntityBehaviourType;
//
//        public TestEntity(TestEntityType entityType, TestEntityBehaviourType beht ) {
//            EntityType = entityType;
//            EntityBehaviourType = beht;
//        }
//
////        // Born abilities - never changes. Sum==100
////        public int BornHungerLevel = 65; // 0-100
////        public int BornFearLevel = 20; // 0-100
////        public int BornMatingLevel = 15; // 0-100
//
//        // Dynamics levels - changes over time up to a max of 100
//        public int CurrentHungerLevel = 10;     // 0 after eat, up when starving (each 1 sec without eating)
//        public int CurrentFearLevel = 12;       // 0 when start, -1 when no fox visible, +1 when fox visible
//        public int CurrentMatingLevel = 30;     // 6-nrofchildren after mating, up when starving (each 1 sec without mating)
//
//        // 65+20+15+10+12+30 = 152
//        // BehaviourPct :
//        // hungerpct = (65+10)/152 * 100 = 49
//        // fearpct = (20+12)/152 * 100 = 21
//        // matepct = (15+30)/152 * 100 = 29
//        //
//        // check: 99.. perfect
//        //
//        // Order er ligegyldig, så stak dem: [hunger:0-49,fear:50-71,mating72-100]
//        //
//        // Throw dice(100): 55
//        //
//        // Result: This rabbit fears!
//        //
//        // Now consider visible entities
//        //      Highest BehaviourPct determines what this rabbit basically will do:;
//        //
//        //  if (fear_is_highest) {
//        //      if (FoxVisible) then RUN AWAY
//        //      else if (Both_Grass_and_rabbit_Is_Visible) then goto highstpct
//        //      else if (Only_Grass_or_rabbit_is_Visible) then goto that
//        //      else  wander_around
//        //  }
//        //  else if (hunger_is_highest) {
//        //      if (GrassIsVisible) then RUN_TOWARDS
//        //      else if (Both_Fox_and_rabbit_Is_Visible) then goto/flee highstpct
//        //      else if (Only_Fox_or_rabbit_is_Visible) then goto/flee that
//        //      else  wander_around
//        //
//        // } else { // Love is in the air
//        //      if (Mate_Is_Visible) then RUN_TOWARDS
//        //      else if (Both_Grass_and_fox_Is_Visible) then goto/flee highstpct
//        //      else if (Only_Grass_or_fox_is_Visible) then goto/flee that
//        //      else  wander_around
//        //
//        // }
//        //
//
//
//    }
//
//    public static boolean FoxIsVBisible(ArrayList<TestEntity> visent) {
//        return visent.stream().anyMatch(testEntity -> testEntity.EntityType == TestEntityType.FOX);
//    }
//
//    public static void TestMakeDecisionForRABBITS(TestEntity currentRabbit, ArrayList<TestEntity> visibleEntities)
//    {
//        if (currentRabbit.EntityType != TestEntityType.RABBIT) {
//            throw new IllegalArgumentException("TestMakeDecisionForRABBITS-> Only rabbits allowed here");
//        }
//
//        Random rnd = new Random();
//        // When Rabbit thinks!
//
//        // Step 1: Throw a dice with this number of sides
//        int NrOfSides = currentRabbit.BornFearLevel+ currentRabbit.BornHungerLevel+ currentRabbit.BornMatingLevel+ currentRabbit.CurrentFearLevel+ currentRabbit.CurrentHungerLevel+currentRabbit.BornMatingLevel;
//
//        int FearThresholdPct = ((currentRabbit.BornFearLevel+currentRabbit.CurrentFearLevel)/NrOfSides) * 100;
//        int HungerThresholdPct = ((currentRabbit.BornHungerLevel+currentRabbit.CurrentHungerLevel)/NrOfSides) * 100;
//        int MatingThresholdPct = ((currentRabbit.BornMatingLevel+currentRabbit.CurrentMatingLevel)/NrOfSides) * 100;
//
//        int outcome = rnd.nextInt(100);
//
//
//
//
//
//
//
//
//        //
////
////        switch (value){
////            case rabbit:
////                // check food level 33,66,95 values should be passed on as genes to the next generation, with a chance of mutation
////                //food 0 = full, 100 = ded
////                    //if below 33
////                            //check dist (with chance to not see everything (visionSuccesRate) or something)
////                            //make list of visible thing sorted by distance
////                            //do whats closest,
////                            //so if the fox is closest, run, if rabbit closest, mate, else wander
////                                //avoid fox
////                                //mate
////                    //if above 33 and below 66
////                            //check dist (with chance to not see everything (visionSuccesRate) or something
////                            //so if the fox is closest, run, if rabbit closest, mate, else wander(eat)
////                                //avoid foX
////                                //mate
////                                //eat
////                    //if above 66 and below 95
////                            //check dist (with chance to not see everything (visionSuccesRate) or something)
////                            //do whats closest,
////                            //priority Eat -> fox -> Mating
////                                //eat
////                                //avoid fox
////                                //mate
////                    //if above 95
////                        //Hail Mary Random stuff
////
////                break;
////            case fox:
////
////                break;
////            case grass:
////
////                break;
////            default:
////
////                break;
////
////
////        }
////
////        // frygtsom kanin - styres primært af sit frygtniveau (p1=frygt,p2=sult,p3=mating)
////
////        // Værdierne nedenfor går fra 0-100 (%) og ændres løbende efterhånden som kaninen
////        // går igennem livet. Dette gælder alle kaniner.
////        int currentFearLevel = 20;
////        int currentHungerLevel = 60;
////        int currentMatingLevel = 10;
////
////        // Frygtkaniner kigger altid på fearlevel først..
////        if (currentFearLevel <= 10) {  // Første værdi (20) bestemmer hvornår kanin er rolig
////            // Kanin er rolig, så kig på prioritet 2 og 3
////
////
////        }
////        else if (currentFearLevel > 10) {
////            // Kanin er skræmt!
////
////        }
////
////        // Rambo-kaniner går efter mad
////        if (currentHungerLevel <= 10) {
////            // Eneste tidspunkt at rambo-kanin IKKE prioriterer med! I denne situation
////            // vil den være rimelig fornuftig, dvs. flygt hvis ræv er synlig, ellers mating ellers vandre videre
////            // if (SomeFoxIsVisible)
////            //    Flee!
////            // else if (SomeBunnyIsVisible)
////            //      AttemptToMate!
////            // else
////            // Nothing to do - go on!
////
////        } else if(currentHungerLevel > 10 && currentHungerLevel < 50) {
////            // Rambo-kanin bliver hurtigt sulten, så her vil den prioritere at spise
////            // selvom der er en damekanin i nærheden. Den flygter dog stadig fra ræve!
////        } if (currentHungerLevel >= 50) {
////            // Alle andre kaniner vill nu enten flygte eller pare sig afh af hbad de ser, men
////            // rambo-kanin vil prioritere mad nu!!
////        }
////
////
////        // Lovebunnies (lol) går efter <3<3<3
////
////
////
////
////
//
//
//
//        System.out.println("i willdo this");
//    }
//
//    public void fearLevelFunction(){
//        //if list returned with fox
//        //then increase the fearlevel with 1, up to a max of 100
//        //if above certain level of fear, then run away
//    }
//    public void matingLevelFunction(){
//        //1 sec timer
//        //increase
//    }
//
//}
