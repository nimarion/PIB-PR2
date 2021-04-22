FROM mcr.microsoft.com/vscode/devcontainers/java:15

RUN su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install maven "

COPY library-scripts/liveshare.sh /tmp/library-scripts/
RUN bash /tmp/library-scripts/liveshare.sh