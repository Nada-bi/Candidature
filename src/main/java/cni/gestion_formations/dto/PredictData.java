package cni.gestion_formations.dto;

public class PredictData {
    private String resume;
    private String[] criteras;
    public String[] getCriteras() {
        return this.criteras;
    }
    public String[] getSplittedResume() {
        return this.resume.split(" ");
    }

}
