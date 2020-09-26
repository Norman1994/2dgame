using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class Save : MonoBehaviour {

    public List<int> livingTargetPositions = new List<int>();
    public List<int> livingTargetsTypes = new List<int>();

    public int lives = 0;
    public string levelName;
}
