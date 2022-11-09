import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class doWhileLoopVisit extends VoidVisitorAdapter<Void> {
    static int count = 0;
    public void visit(DoStmt d, Void arg)
    {
        count += 1;
        System.out.println("Found a do while statement @ " + d.toString());
    }
}
