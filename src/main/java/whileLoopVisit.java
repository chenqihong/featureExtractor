import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class whileLoopVisit extends VoidVisitorAdapter<Void> {
    static int count = 0;
    public void visit(WhileStmt w, Void arg)
    {
        count += 1;
        System.out.println("Found an while statement @ " + w.toString());
    }

}
