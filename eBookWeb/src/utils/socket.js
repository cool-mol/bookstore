import io from 'socket.io-client';
const HOST="ws://localhost:8080/";
export default io(HOST);