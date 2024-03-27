package gitlet;


/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author Ziyue Shen
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            System.exit(0);
        }
        String firstArg = args[0];
        switch (firstArg) {    // must all be static methods
            case "init":
                Repository.initCommand();
                break;
            case "add":
                Repository.checkInit();
                if (args.length == 1) {
                    System.out.println("Incorrect operands.");
                    System.exit(0);
                }
                Repository.add(args[1]);
                break;
            case "commit":
                Repository.checkInit();
                if (args.length >= 2 && !args[1].isEmpty()) {
                    Repository.commit(args[1]);
                } else {
                    System.out.println("Please enter a commit message.");
                }
                break; // must have, otherwise will go to the next block
            case "log":
                Repository.checkInit();
                Repository.log();
                break;
            case "global-log":
                Repository.checkInit();
                Repository.globalLog();
                break;
            case "find":
                Repository.checkInit();
                if (args.length == 1) {
                    System.out.println("Incorrect operands.");
                    System.exit(0);
                }
                Repository.find(args[1]);
                break;
            case "checkout":
                Repository.checkInit();
                // first read args.length on the left side, avoid index error
                if (args.length == 3 && args[1].equals("--")) {
                    Repository.checkout("HEAD", args[2]);
                } else if (args.length == 2) {
                    Repository.checkoutBranch(args[1]);
                } else if (args.length == 4 && args[2].equals("--")) {
                    Repository.checkout(args[1], args[3]);
                } else {
                    System.out.println("Incorrect operands.");
                }
                break;
            case "rm":
                Repository.checkInit();
                if (args.length == 1) {
                    System.out.println("Incorrect operands.");
                    System.exit(0);
                }
                Repository.rm(args[1]);
                break;
            case "branch":
                Repository.checkInit();
                if (args.length == 1) {
                    System.out.println("Incorrect operands.");
                    System.exit(0);
                }
                Repository.branch(args[1]);
                break;
            case "rm-branch":
                Repository.checkInit();
                if (args.length == 1) {
                    System.out.println("Incorrect operands.");
                    System.exit(0);
                }
                Repository.rmBranch(args[1]);
                break;
            case "merge":
                Repository.checkInit();
                if (args.length == 1) {
                    System.out.println("Incorrect operands.");
                    System.exit(0);
                }
                Repository.merge(args[1]);
                break;
            case "status":
                Repository.checkInit();
                Repository.status();
                break;
            case "reset":
                Repository.checkInit();
                if (args.length == 1) {
                    System.out.println("Incorrect operands.");
                    System.exit(0);
                }
                Repository.reset(args[1]);
                break;
            default:
                System.out.println("No command with that name exists.");
                System.exit(0);
        }
    }
}
