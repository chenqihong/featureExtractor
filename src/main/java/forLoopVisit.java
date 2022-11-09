import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class forLoopVisit extends VoidVisitorAdapter<Void> {
    static int count = 0;
    public void visit(ForStmt f, Void arg)
    {
        count += 1;
        System.out.println("Found a do while statement @ " + f.toString());
    }
}
