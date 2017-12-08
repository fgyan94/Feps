package feps;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

public class SerialComm implements SerialPortEventListener {
	boolean portFound = false;
	SerialPort serialPort;

	public void serialEvent(SerialPortEvent event) {

		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			// Se tiver dados disponíveis
			// byte[] readBuffer = new byte[20];
			int novoDado = 0;
			StringBuilder dados = new StringBuilder();
			try {
				while (novoDado != -1) {
					novoDado = inputStream.read();
					if (novoDado == -1)
						break;
					if ('\r' == (char) novoDado)
						dados.append('\n');
					else
						dados.append((char) novoDado);
				}
				System.out.println(dados);

				// int numBytes = 0;
				// while (inputStream.available() > 0) {
				// numBytes = inputStream.read(readBuffer);
				// }
				// String result = new String(readBuffer);
				// System.out.println("Read: " + result);
				// System.out.println("Bytes Read: " + numBytes);

			} catch (IOException e) {
			}

			break;
		}
	}

	private String getPortNameByOS() {

		String osname = System.getProperty("os.name", "").toLowerCase();
		if (osname.startsWith("windows")) {
			return "COM1"; // Se estiver no Windows
		} else if (osname.startsWith("linux")) {
			return "/dev/ttyS0"; // Se estiver no Linux
		} else if (osname.startsWith("mac")) {
			return "???"; // No mac eu não sei
		} else {
			System.out.println("Seu S.O. não é tem suporte ainda!"); // Se não for nenhum deles.
			System.exit(1);
			return null;
		}

	}

	private CommPortIdentifier getPortIdentifier(String portName) {

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Pega os identificadores de porta
		Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
		// Varre as possíveis portas
		while (portList.hasMoreElements()) {
			CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
			// Verifica se a porta é a serial
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				System.out.println("Available port: " + portId.getName());
				// Verifica se é o identificador correto (COM1 ou ttsy0)
				if (portId.getName().equals(portName)) {
					System.out.println("Found port: " + portName);
					portFound = true;
					return portId;
				}
			}

		}

		return null;

	}

	InputStream inputStream; // O InputStream fica fora porque é utilizado depois
	OutputStream outputStream;

	public void execute() {
		// Pega a porta pelo S.O.
		String portName = getPortNameByOS();
		// Retorna o identificador da porta
		CommPortIdentifier portId = getPortIdentifier(portName);
		// Se o PortId for nulo, não há porta disponível
		if (portId != null) {

			try {
				// Abre a porta serial solicitada
				serialPort = (SerialPort) portId.open(this.getClass().getName(), 2000);

				// Pega o InputStream da Porta Serial
				inputStream = serialPort.getInputStream();
				outputStream = serialPort.getOutputStream();

				// Cria um novo Listener de Eventos
				serialPort.addEventListener(this);

				// Avisa se tive alguma mudança na Porta Serial
				serialPort.notifyOnDataAvailable(true);

				// Passa os parametros da porta serial
				serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);
			} catch (PortInUseException e) {

			}
			// Implente sua exception aqui
			catch (IOException e) {

			}
			// Implente sua exception aqui
			catch (UnsupportedCommOperationException e) {
				e.printStackTrace();
			} catch (TooManyListenersException e) { // Implente sua exception aqui}

			}
		} else {
			System.out.println("Porta Serial não disponível");
		}
	}

	public void write(String msg) {
		try {
			outputStream = serialPort.getOutputStream();
			outputStream.write(msg.getBytes());
			Thread.sleep(100);
			outputStream.flush();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}