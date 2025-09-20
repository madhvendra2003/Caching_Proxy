# Caching Proxy Server

A Spring Boot-based caching proxy server that forwards HTTP requests to an origin server and caches responses using Guava Cache. The proxy supports process management through shell commands and provides a RESTful API interface.

## Features

- HTTP request forwarding to configurable origin server
- In-memory caching using Guava Cache
- Process management via shell commands (start/stop/status)
- RESTful API interface
- Configurable cache expiry and size
- Support for multiple HTTP methods (GET, POST, PUT, DELETE, PATCH)

## Prerequisites

- Java 21 or higher
- Maven 3.x
- Bash (for running shell scripts)

## Installation

1. Clone the repository
2. Build the project:
```bash
mvn clean package
```

## Usage

### Using Shell Commands

The proxy server can be managed using the following shell commands:

1. **Start the proxy**:
```bash
./caching-proxy.sh start --port <number> --origin <url>
```

2. **Stop the proxy**:
```bash
./caching-proxy.sh stop --port <number>
```

3. **Check status**:
```bash
./caching-proxy.sh status
```

### Configuration

The proxy can be configured through:

1. **application.properties**:
```properties
spring.application.name=caching-proxy
spring.shell.interactive.enabled=true
spring.shell.command.script.enabled=true
proxy.origin=https://your-origin-server.com
```

2. **Command line arguments**:
- `--port`: The port number for the proxy server
- `--origin`: The origin server URL

## Cache Configuration

The cache is configured with:
- Maximum size: 100 entries
- Expiry: 10 minutes after write
- LRU (Least Recently Used) eviction policy

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/madhvendra/caching/proxy/
│   │       ├── controller/
│   │       ├── services/
│   │       ├── utility/
│   │       │   ├── cache/
│   │       │   └── restclient/
│   │       └── CachingProxyApplication.java
│   └── resources/
└── test/
```

## Key Components

- **UrlController**: Handles incoming HTTP requests
- **ProxyServices**: Manages caching and request forwarding
- **GuavaCache**: Implements caching using Google Guava
- **RestCall**: Handles HTTP requests to the origin server

## Process Management

The proxy server supports multiple instances running on different ports. Each instance:
- Has its own PID file in `/tmp/caching-proxy/`
- Logs to a separate file
- Can be managed independently

## Logging

Logs are stored in:
- `/tmp/caching-proxy/<port>.log` for each instance
- Application-level logging uses Spring Boot's default logging configuration

## Error Handling

- Cache misses are handled gracefully with origin server requests
- Process management errors are reported with clear error messages
- HTTP errors from the origin server are properly propagated

## Development

To contribute to the project:

1. Fork the repository
2. Create a feature branch
3. Submit a pull request

## Testing

Run the tests using:
```bash
mvn test
```

## License

This project is licensed under the MIT License - see the LICENSE file for details.
