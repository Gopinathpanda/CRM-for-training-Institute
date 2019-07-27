
package model;
public class Module {
 private int module_id;
 private String module_name;
 private int duration_module;
 private int program_id;
 private String course_name;

    public Module() {
    }

    public Module(int module_id, String module_name, int duration_module, int program_id, String course_name) {
        this.module_id = module_id;
        this.module_name = module_name;
        this.duration_module = duration_module;
        this.program_id = program_id;
        this.course_name = course_name;
    }

    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public int getDuration_module() {
        return duration_module;
    }

    public void setDuration_module(int duration_module) {
        this.duration_module = duration_module;
    }

    public int getProgram_id() {
        return program_id;
    }

    public void setProgram_id(int program_id) {
        this.program_id = program_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
}
