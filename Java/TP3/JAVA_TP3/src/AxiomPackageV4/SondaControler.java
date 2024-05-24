package AxiomPackageV4;

public abstract class SondaControler {

   public static SondaControler createInitialState() {
        return new SondaNotDeployed();
   }

   public abstract void canMove();

   public abstract void canDeploy();
   public abstract boolean isDeployed();
   public abstract void canSlowDown();

   public abstract  void deploySonda();

   public abstract void retractSonda();

   public abstract boolean whatAmI();


}