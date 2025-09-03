package org.example.groworders.domain.weather.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWeather is a Querydsl query type for Weather
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWeather extends EntityPathBase<Weather> {

    private static final long serialVersionUID = -1236427286L;

    public static final QWeather weather = new QWeather("weather");

    public final StringPath hm = createString("hm");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath predictYield = createString("predictYield");

    public final StringPath rn = createString("rn");

    public final StringPath si = createString("si");

    public final DatePath<java.time.LocalDate> sowingDate = createDate("sowingDate", java.time.LocalDate.class);

    public final StringPath stn = createString("stn");

    public final StringPath ta = createString("ta");

    public final StringPath tm = createString("tm");

    public final StringPath ws = createString("ws");

    public QWeather(String variable) {
        super(Weather.class, forVariable(variable));
    }

    public QWeather(Path<? extends Weather> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWeather(PathMetadata metadata) {
        super(Weather.class, metadata);
    }

}

