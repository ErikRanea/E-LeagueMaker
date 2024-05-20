#!/bin/bash
# Configuración de Oracle Client
export ORACLE_HOME=/usr/lib/oracle/19.3/client64
export PATH=$ORACLE_HOME/bin:$PATH
export LD_LIBRARY_PATH=$ORACLE_HOME/lib:$LD_LIBRARY_PATH
export TNS_ADMIN=$ORACLE_HOME/network/admin

set -euo pipefail  # Habilitar opciones para un mejor manejo de errores

# Definir las credenciales y el servidor
USERNAME="proyecto1"
PASSWORD="MasTrabajo24"
HOST="a8150ad3dbd3.sn.mynetname.net"
PORT="33159"
SERVICE_NAME="xe"

# Ruta de los archivos SQL
SQL_FILES_PATH="./basesDeDatos/Fase2"

# Crear archivo de log
LOG_FILE="ejecutador_sql.log"
echo "Inicio de ejecución: $(date)" > $LOG_FILE

# Función para abrir conexión con Oracle usando SQLPlus
abrirConexion() {
  echo "Conexión abierta con la base de datos." >> $LOG_FILE
  # Comandos adicionales para abrir la conexión podrían ir aquí si fuera necesario
}

# Función para cerrar conexión con Oracle
cerrarConexion() {
  echo "Conexión cerrada con la base de datos." >> $LOG_FILE
  # Comandos adicionales para cerrar la conexión podrían ir aquí si fuera necesario
}

# Función para cargar y ejecutar un archivo SQL
cargarScripsSQL() {
  local sql_file=$1
  echo "Ejecutando $sql_file..."
  echo "Ejecutando $sql_file..." >> $LOG_FILE
  echo "connect $USERNAME/$PASSWORD@$HOST:$PORT/$SERVICE_NAME
  @${sql_file}
  EXIT;" | sqlplus -s /nolog >> $LOG_FILE 2>&1
  if [ $? -ne 0 ]; then
    echo "Error al ejecutar $sql_file" >> $LOG_FILE
    return 1  # Retorna un estado de error
  fi
  echo "$sql_file ejecutado correctamente." >> $LOG_FILE
}

# Abrir conexión
abrirConexion

# Conectarse a Oracle y ejecutar cada archivo SQL encontrado en la carpeta
for sql_file in $(find $SQL_FILES_PATH -name '*.sql' | sort)
do
  cargarScripsSQL $sql_file
  if [ $? -ne 0 ]; then
    echo "Error al ejecutar $sql_file, revisar el log en $LOG_FILE"
    cerrarConexion
    exit 1  # Detener la ejecución si ocurre un error
  fi
  done

# Cerrar conexión
cerrarConexion

echo "Todos los scripts han sido ejecutados correctamente."
echo "Fin de ejecución: $(date)" >> $LOG_FILE
