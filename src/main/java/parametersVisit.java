import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class parametersVisit extends VoidVisitorAdapter<Void> {
    static int count = 0;
    public void visit(Parameter r, Void arg)
    {
        count += 1;
        System.out.println("Found a parameter statement @ " + r.toString());
    }
}
