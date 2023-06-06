package com.example.example.components;

import org.springframework.integration.endpoint.AbstractFetchLimitingMessageSource;

public class FetchLimitHttpSource extends AbstractFetchLimitingMessageSource<String> {
    @Override
    protected Object doReceive(int maxFetchSizeToReceive) {
        return null;
    }

    @Override
    public String getComponentType() {
        return null;
    }

    @Override
    public <T> T getThisAs() {
        return super.getThisAs();
    }
}
