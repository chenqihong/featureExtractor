import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class methodDeclareVisit extends VoidVisitorAdapter<Void> {
    static int privateCount = 0;
    static int defaultCount = 0;
    static int publicCount = 0;
    static int protectedCount = 0;
    public void visit(MethodDeclaration m, Void arg){
        AccessSpecifier accessSpecifier = m.getAccessSpecifier();
        if (accessSpecifier.toString() == null){
            defaultCount+=1;
        }
        else if (accessSpecifier.toString().equals("Private")){
            privateCount += 1;
        }
        else if (accessSpecifier.toString().equals("Protected")){
            protectedCount += 1;
        }
        else{
            publicCount += 1;
        }
    }
}
