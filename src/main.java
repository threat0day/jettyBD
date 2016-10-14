
class Main {
    public static void main(String[] args)throws Exception {

        gameMechanic GM=new gameMechanic();
        workStatement workStatement=new workStatement();
        workStatement.executeStatement(gameMechanic.stateUpdate);

        startServer startServer=new startServer();
    }
}
