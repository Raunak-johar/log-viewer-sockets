# log-viewer-sockets
Real-time Log Viewer (Unix "tail -f") with Spring Boot &amp; WebSockets

## Objective
Stream updates from a log file to a web page in real-time, like `tail -f` command.

- **Server**: monitors the log file and pushes new lines.
- **Client**: displays updates without page refresh.
- Shows **last 10 lines** on first load.

## Constraints

- Support **large files** (GBs) efficiently, you can not read the complete file into the memory.
- Only send **new lines**, not the whole file.
- Handle **multiple clients** simultaneously.
- **Single-page client**, no reloads.
- **No external tail-like libraries use**.

## Testing

You can run this script to generate test logs:

```bash
while true; do
  echo "Log entry at $(date)" >> logs.txt 
  sleep 1
done
