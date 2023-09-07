package ru.home.dto.request;

import org.apache.velocity.VelocityContext;
import ru.home.velocity.VelocityContextCreator;

import java.util.List;

public record PersonRequestDto(
        String name,
        List<Integer> id) implements VelocityContextCreator {
    @Override
    public VelocityContext toVelocityContext() {
        VelocityContext context = new VelocityContext();
        context.put("requestDTO", this);
        return context;
    }
}
