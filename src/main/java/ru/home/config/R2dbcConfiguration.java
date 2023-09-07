package ru.home.config;

import io.r2dbc.spi.ConnectionFactory;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.binding.BindMarkersFactory;
import org.springframework.r2dbc.core.binding.BindMarkersFactoryResolver;

/**
 * Конфигурационный класс для R2DBC клиента
 */
@Configuration
@Setter
public class R2dbcConfiguration implements BindMarkersFactoryResolver.BindMarkerFactoryProvider {

    /**
     * Clickhouse-а нет в поддержке Spring R2BDC, поэтому не находит соответствующий
     * {@link BindMarkersFactory}.
     * Параметры в SQL запросе с префиксом ":".
     */
    @Override
    public BindMarkersFactory getBindMarkers(ConnectionFactory connectionFactory) {
        return BindMarkersFactory.named(":", "p", 1024);
    }
}