package app.ui.console;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class DevTeamUI implements Runnable{

    public DevTeamUI()
    {

    }
    public void run()
    {
        System.out.println("\n");
        System.out.printf("Development Team:\n");
        System.out.printf("\t João Moreira - 1190709@isep.ipp.pt \n");
        System.out.printf("\t Jorge Ferreira - 1201564@isep.ipp.pt \n");
        System.out.printf("\t Rafael Leite - 1201566@isep.ipp.pt \n");
        System.out.printf("\t Rui Pina - 1201568@isep.ipp.pt \n");
        System.out.printf("\t Santiago Azevedo - 1201623@isep.ipp.pt \n");
        System.out.println("\n");
    }
}
