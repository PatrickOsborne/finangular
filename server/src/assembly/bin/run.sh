#!/bin/bash -x

PRG="$0"

while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

PRGDIR=`dirname "$PRG"`

[ -z "$FANG_HOME" ] && FANG_HOME=`cd "$PRGDIR/.." >/dev/null; pwd`

[ -z "$DEV_HOME" ] && DEV_HOME=`cd "$PRGDIR/../../../../" >/dev/null; pwd`

[ -z "$LOG_DIR" ] && LOG_DIR="$FANG_HOME/logs"

if [ "$1" = "jpda" ] ; then
  if [ -z "$JPDA_TRANSPORT" ]; then
    JPDA_TRANSPORT="dt_socket"
  fi
  if [ -z "$JPDA_ADDRESS" ]; then
    JPDA_ADDRESS="localhost:8000"
  fi
  if [ -z "$JPDA_SUSPEND" ]; then
    JPDA_SUSPEND="n"
  fi
  if [ -z "$JPDA_OPTS" ]; then
    JPDA_OPTS="-agentlib:jdwp=transport=$JPDA_TRANSPORT,address=$JPDA_ADDRESS,server=y,suspend=$JPDA_SUSPEND"
  fi

  SERVER_JVM_OPTS="$JPDA_OPTS $SERVER_JVM_OPTS"
  shift
fi

MAIN_CLASS="org.ozzysoft.finangular.server.http.ServerMain"

JAVA_MEM_OPTS="-Xms128M -Xmx256M "
SERVER_OPTS=""
JAVA_OPTS="$JAVA_OPTS $JAVA_MEM_OPTS $SERVER_JVM_OPTS"

SERVER_CP="$FANG_HOME/config/:$FANG_HOME/lib/*"

FINATRA_OPTS="-http.port=:18100 -admin.port=:18106"
if [ "$DEV_MODE" = "true" ] ; then
    echo "Running in dev mode"
    FINATRA_OPTS="$FINATRA_OPTS -local.doc.root=$DEV_HOME/src/main/resources/client"
else
    FINATRA_OPTS="$FINATRA_OPTS -doc.root=/client"
fi

pushd $FANG_HOME
if [ "$DEV_MODE" = "true" ] ; then
    exec java ${JAVA_OPTS} ${SERVER_OPTS} -cp ${SERVER_CP} ${MAIN_CLASS} ${FINATRA_OPTS} $@
else
    exec java ${JAVA_OPTS} ${SERVER_OPTS} -cp ${SERVER_CP} ${MAIN_CLASS} ${FINATRA_OPTS} $@
fi

