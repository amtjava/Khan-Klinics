export default function uuid() {
    return new Date().getTime() + Math.random().toString(36).substr(2);
}