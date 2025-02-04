#!/bin/bash

if [ -z "$WEATHER_API_KEY" ]; then
    echo "Error: WEATHER_API_KEY is not set"
    exit 1
fi

if [ -z "$BRAVE_SEARCH_API_KEY" ]; then
    echo "Error: BRAVE_SEARCH_API_KEY is not set"
    exit 1
fi

exec "$@"
