package ru.test.sibers.pokeapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokemon {

    @SerializedName("id")
    private Integer id;
    @SerializedName("num")
    private String num;
    @SerializedName("name")
    private String name;
    @SerializedName("img")
    private String img;
    @SerializedName("type")
    private List<String> type;
    @SerializedName("height")
    private String height;
    @SerializedName("weight")
    private String weight;
    @SerializedName("candy")
    private String candy;
    @SerializedName("candy_count")
    private Integer candyCount;
    @SerializedName("egg")
    private String egg;
    @SerializedName("spawn_chance")
    private Double spawnChance;
    @SerializedName("avg_spawns")
    private Double avgSpawns;
    @SerializedName("spawn_time")
    private String spawnTime;
    @SerializedName("multipliers")
    private List<Double> multipliers;
    @SerializedName("weaknesses")
    private List<String> weaknesses;
    @SerializedName("prev_evolution")
    private List<Evolution> prevEvolution;
    @SerializedName("next_evolution")
    private List<Evolution> mEvolution;

    public Pokemon() {
    }

    public Pokemon(Integer id, String num, String name, String img, List<String> type, String height, String weight, String candy, Integer candyCount, String egg, Double spawnChance, Double avgSpawns, String spawnTime, List<Double> multipliers, List<String> weaknesses, List<Evolution> prevEvolution, List<Evolution> evolution) {
        this.id = id;
        this.num = num;
        this.name = name;
        this.img = img;
        this.type = type;
        this.height = height;
        this.weight = weight;
        this.candy = candy;
        this.candyCount = candyCount;
        this.egg = egg;
        this.spawnChance = spawnChance;
        this.avgSpawns = avgSpawns;
        this.spawnTime = spawnTime;
        this.multipliers = multipliers;
        this.weaknesses = weaknesses;
        this.prevEvolution = prevEvolution;
        this.mEvolution = evolution;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCandy() {
        return candy;
    }

    public void setCandy(String candy) {
        this.candy = candy;
    }

    public Integer getCandyCount() {
        return candyCount;
    }

    public void setCandyCount(Integer candyCount) {
        this.candyCount = candyCount;
    }

    public String getEgg() {
        return egg;
    }

    public void setEgg(String egg) {
        this.egg = egg;
    }

    public Double getSpawnChance() {
        return spawnChance;
    }

    public void setSpawnChance(Double spawnChance) {
        this.spawnChance = spawnChance;
    }

    public Double getAvgSpawns() {
        return avgSpawns;
    }

    public void setAvgSpawns(Double avgSpawns) {
        this.avgSpawns = avgSpawns;
    }

    public String getSpawnTime() {
        return spawnTime;
    }

    public void setSpawnTime(String spawnTime) {
        this.spawnTime = spawnTime;
    }

    public List<Double> getMultipliers() {
        return multipliers;
    }

    public void setMultipliers(List<Double> multipliers) {
        this.multipliers = multipliers;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<Evolution> getPrevEvolution() {
        return prevEvolution;
    }

    public void setPrevEvolution(List<Evolution> prevEvolution) {
        this.prevEvolution = prevEvolution;
    }

    public List<Evolution> getEvolution() {
        return mEvolution;
    }

    public void setEvolution(List<Evolution> evolution) {
        this.mEvolution = evolution;
    }

}
