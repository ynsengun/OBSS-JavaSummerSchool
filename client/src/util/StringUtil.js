export function truncateWithDots(str) {
  if (str.length > 12) {
    return str.substring(0, 9) + "...";
  }
  return str;
}
