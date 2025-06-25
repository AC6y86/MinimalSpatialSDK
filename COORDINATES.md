# Spatial SDK Coordinate System - Comprehensive Analysis

## Coordinate Axes
- **X-axis**: Left (negative) to Right (positive)
- **Y-axis**: Down (negative) to Up (positive)  
- **Z-axis**: Into screen (negative) to Out of screen (positive)

## Visible Viewport at Z = 5.0
Based on detailed experiments:

### X-axis bounds
- **Visible range**: -4.0 to +4.0 (8 units wide)
- **Center**: X = 0.0
- **Comfortable content area**: -3.0 to +3.0

### Y-axis bounds  
- **Visible range**: -0.5 to +3.0 (3.5 units tall)
- **Center**: Approximately Y = 1.25
- **Comfortable content area**: 0.0 to 2.5

### Z-axis considerations
- Default viewing distance: Z = 5.0
- Objects closer than Z = 3.0 may be too large
- Objects farther than Z = 8.0 may be too small

## Optimal Tic-Tac-Toe Board Positioning

### Recommended Configuration
```kotlin
val boardCenterY = 1.5f  // Center of visible Y range
val boardSize = 2.4f     // 80% of visible Y height
val distance = 5.0f      // Standard viewing distance
```

### Grid Line Positions (for equal cells)
With boardSize = 2.4f:
- **Horizontal lines**: 
  - Top: Y = boardCenterY + boardSize/6 = 1.9
  - Bottom: Y = boardCenterY - boardSize/6 = 1.1
- **Vertical lines**:
  - Left: X = -boardSize/6 = -0.4
  - Right: X = +boardSize/6 = +0.4

### Token Positions (9 cells)
Each cell is 0.8 x 0.8 units (boardSize/3):

**Top Row (Y = 2.1)**
- Top-left: (-0.8, 2.1, 5.0)
- Top-center: (0.0, 2.1, 5.0)
- Top-right: (0.8, 2.1, 5.0)

**Middle Row (Y = 1.5)**
- Middle-left: (-0.8, 1.5, 5.0)
- Middle-center: (0.0, 1.5, 5.0)
- Middle-right: (0.8, 1.5, 5.0)

**Bottom Row (Y = 0.9)**
- Bottom-left: (-0.8, 0.9, 5.0)
- Bottom-center: (0.0, 0.9, 5.0)
- Bottom-right: (0.8, 0.9, 5.0)

## Rotation for X Tokens
For creating diagonal X marks:
- 45° rotation: Quaternion(0, 0, 0.383, 0.924)
- -45° rotation: Quaternion(0, 0, -0.383, 0.924)

## Key Insights from Experiments
1. The viewport is wider (8 units) than it is tall (3.5 units)
2. The visible Y range starts below 0, so centering at Y=1.5 is optimal
3. Content at Y > 3.0 or Y < -0.5 will be clipped
4. The X-axis has plenty of room for side-by-side content
5. For the original board at Y=2.2, the top row tokens at Y=2.95 were barely visible

## Recommendations
1. Use Y=1.5 as the board center for better visibility
2. Keep board size around 2.4 units for comfortable viewing
3. Position tokens at least 0.1 units in front of grid lines (smaller Z)
4. Use smaller tokens (0.2-0.3 units) to fit comfortably in cells

## Additional Findings from Implementation

### Token Positioning Adjustments
- For better centering, tokens may need individual Y adjustments:
  - Top row tokens: Add 0.1f to Y position to center in cells
  - Bottom row tokens: Subtract 0.1f from Y position to center in cells
  - This compensates for visual perception differences at different heights

### Creating X Tokens
- Quaternion rotations alone don't create visually clear X shapes
- Better approach: Use multiple small boxes positioned diagonally
- Successful X token pattern:
  ```kotlin
  // 5 boxes total: corners + center
  // Corner positions at ±offset from center
  val offset = 0.06f  // Half the X size
  // Top-left, Top-right, Center, Bottom-left, Bottom-right
  ```
  - This creates a clear X shape visible from all angles
  - Each piece is a small cube (0.08f × 0.08f × 0.04f)

### Z-Depth Considerations
- Tokens should be positioned 0.5 units closer than walls to avoid occlusion
- For wall at Z=5.0f, tokens should be at Z=4.5f
- This ensures top row tokens aren't hidden by the top wall

### Final Working Configuration
```kotlin
val boardCenterY = 1.5f
val boardSize = 2.4f
val wallDistance = 5.0f
val tokenZ = wallDistance - 0.5f  // 4.5f

// Token positions with adjustments
// Top row: Y = 2.1f + 0.1f = 2.2f
// Middle row: Y = 1.5f
// Bottom row: Y = 0.9f - 0.1f = 0.8f
```