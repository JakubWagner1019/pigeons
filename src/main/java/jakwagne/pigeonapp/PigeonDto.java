package jakwagne.pigeonapp;

public record PigeonDto(String id,
                        String description,
                        Integer gender) {
    public Pigeon toPigeon() {
        return new Pigeon(
                this.id,
                this.description,
                this.gender,
                null,
                null);
    }
}
