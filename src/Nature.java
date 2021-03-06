public class Nature {
   public static final double H = 1000;
   
   public static void main(String[] args) {
      double[] beingConsumed = {0.0, .01};// length of species considered
      double[] consumingOthers = {0.0, 0.0};
      Creature prey = new Creature(.4, 0, beingConsumed, consumingOthers, 100, "prey");
      
      
      double[] pBeingConsumed = {0.0, 0.0};// length of species considered
      double[] pConsumingOthers = {0.0, .005};
      Creature predator = new Creature(0, .3, pBeingConsumed, pConsumingOthers, 100, "predator");
      
      Creature[] creatures = new Creature[2];
      creatures[0] = prey;
      creatures[1] = predator;
      
      for(int i = 0; i < 1000; i++) {
         for(Creature current : creatures) {
         double consumedSum = 0;
         double growthSum = 0;
         for(int j = 0; j < beingConsumed.length; j++) {
            consumedSum = consumedSum + current.population * 
                  current.consumptionRate[j] * creatures[j].population;
                  
            growthSum = growthSum + current.population * 
                  current.growthByConsumptionRate[j] * creatures[j].population;
                  
                  
         }
         double birthRate = current.trueBirthRate * current.population;
         double deathRate = current.trueDeathRate * current.population;
         
         current.population = current.population + ((birthRate + growthSum - consumedSum - deathRate) / 1000.0);
         
         System.out.println(current + " " + current.population);
         }
      }
   }
   
   
   
   public static double calculateK(double yn, double k, double constant) {
	   return yn + constant * H * k;
   }
   
   
   public static double calculateNextStep(double yn, double k1, double k2, double k3, double k4) {
	   return yn + ((double) H/6.0) * (k1 + 2 * k2 + 2 * k3 + k4);
   }
}