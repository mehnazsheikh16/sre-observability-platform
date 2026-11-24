# Incident 001: Simulated HTTP 5xx Failure

## 1. Summary

A controlled failure simulation was conducted against the Spring Boot Order Service to validate HTTP error monitoring and automated alerting.

The test successfully demonstrated failure detection, alert escalation, and recovery verification.

## 2. Environment

- **Application:** Spring Boot Order Service
- **Technology:** Java 25, Spring Boot 4.2.0
- **Monitoring:** Prometheus and Grafana
- **Infrastructure:** Local development environment with Docker Desktop
- **Affected endpoint:** `/api/orders/simulate-error`

## 3. Detection

The Order Service exposed HTTP metrics through Spring Boot Actuator and Micrometer.

Prometheus collected metrics every 15 seconds, while Grafana evaluated the HTTP 5xx error rate.

The configured alert triggered when the error rate exceeded 5% for a two-minute pending period.

## 4. Root Cause

A temporary testing endpoint intentionally threw an unhandled `RuntimeException`, causing HTTP 500 responses.

This was a controlled simulation, not an actual production outage.

## 5. Investigation and Impact

- Generated multiple failing HTTP requests.
- Verified that Prometheus recorded the failures.
- Observed an increase in the HTTP 5xx Error Rate dashboard.
- Confirmed that Grafana transitioned from Pending to Firing.
- Verified that the application remained running during the simulated failures.

## 6. Mitigation and Recovery

The failure-generation process was stopped.

As the historical failures left the monitoring window, the measured error rate decreased.

Grafana subsequently returned the alert to Normal, confirming successful recovery from the simulated incident.

## 7. Lessons Learned

1. A healthy application process does not guarantee successful business operations.
2. HTTP error-rate monitoring detects problems that health checks alone may miss.
3. Alert pending periods help reduce unnecessary alerts from short-lived spikes.
4. Recovery should be verified through monitoring data and alert state transitions.

## 8. Follow-up Actions

- Remove or disable the simulation endpoint before public deployment.
- Implement structured application logging.
- Introduce distributed tracing with OpenTelemetry.
- Add monitoring for application latency and downstream service availability.
- Create additional reliability tests for Inventory and Payment services.
