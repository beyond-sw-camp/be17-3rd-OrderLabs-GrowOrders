package org.example.groworders.domain.predict.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPrediction is a Querydsl query type for Prediction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPrediction extends EntityPathBase<Prediction> {

    private static final long serialVersionUID = 1260696766L;

    public static final QPrediction prediction = new QPrediction("prediction");

    public final StringPath cropName = createString("cropName");

    public final StringPath cultivationType = createString("cultivationType");

    public final NumberPath<Double> cumulativeSolarMax = createNumber("cumulativeSolarMax", Double.class);

    public final NumberPath<Double> cumulativeSolarMin = createNumber("cumulativeSolarMin", Double.class);

    public final StringPath growthStage = createString("growthStage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> outsideTempMax = createNumber("outsideTempMax", Double.class);

    public final NumberPath<Double> outsideTempMin = createNumber("outsideTempMin", Double.class);

    public final NumberPath<Double> weeklyAvgHumidityMax = createNumber("weeklyAvgHumidityMax", Double.class);

    public final NumberPath<Double> weeklyAvgHumidityMin = createNumber("weeklyAvgHumidityMin", Double.class);

    public final NumberPath<Double> yield = createNumber("yield", Double.class);

    public QPrediction(String variable) {
        super(Prediction.class, forVariable(variable));
    }

    public QPrediction(Path<? extends Prediction> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPrediction(PathMetadata metadata) {
        super(Prediction.class, metadata);
    }

}

