package se.lars;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "port-select", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST)
public class PortSelectorMojo extends AbstractMojo {
    @Parameter( defaultValue = "${project}", readonly = true )
    protected MavenProject project;

    @Parameter(property = "exec.inputFile")
    private String inputFile;

    @Parameter(property = "exec.outputFile")
    private String outputFile;

    @Override
    public void execute() {
        System.out.println("executing plugin: " + inputFile + " - " + outputFile);
        project.getProperties().setProperty("mock.port", "8080");
    }

    
}
