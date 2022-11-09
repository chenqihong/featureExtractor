import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class returnVisit extends VoidVisitorAdapter<Void> {
    static int count = 0;
    public void visit(ReturnStmt r, Void arg)
    {
        count += 1;
        System.out.println("Found a return statement @ " + r.toString());
    }

}
