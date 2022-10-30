package sollute.estoquecerto.responses.extrato;

public class ListaExtratoResponse {

    private String extractName;
    private String extractTime;
    private Double extractAmount;
    private Integer extract_type;

    public ListaExtratoResponse(String extractName, String extractTime, Double extractAmount, Integer extract_type) {
        this.extractName = extractName;
        this.extractTime = extractTime;
        this.extractAmount = extractAmount;
        this.extract_type = extract_type;
    }

    public Integer getExtract_type() {
        return extract_type;
    }

    public void setExtract_type(Integer extract_type) {
        this.extract_type = extract_type;
    }

    public String getExtractName() {
        return extractName;
    }

    public void setExtractName(String extractName) {
        this.extractName = extractName;
    }

    public String getExtractTime() {
        return extractTime;
    }

    public void setExtractTime(String extractTime) {
        this.extractTime = extractTime;
    }

    public Double getExtractAmount() {
        return extractAmount;
    }

    public void setExtractAmount(Double extractAmount) {
        this.extractAmount = extractAmount;
    }
}
